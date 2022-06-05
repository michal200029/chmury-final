package tm.chmury.invoicegenerator;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.pdf.BaseFont;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tm.chmury.invoicegenerator.model.OrderDetail;
import tm.chmury.invoicegenerator.model.OrderItem;
import tm.chmury.invoicegenerator.repository.OrderDetailRepository;
import tm.chmury.invoicegenerator.repository.OrderItemRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@Component
@AllArgsConstructor
public class InvoiceGenerator {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderItemRepository orderItemRepository;

    
    public void generateInvoice(String orderTrackingNumber) {
        try {

            OrderDetail orderDetail = orderDetailRepository.findByOrderTrackingNumber(orderTrackingNumber);
            List<OrderItem> orderItems = orderItemRepository.findAllByOrderDetailId(orderDetail.getId());

            UUID invoiceId = UUID.randomUUID();
            orderDetail.setInvoiceId(invoiceId);


            PdfWriter pdfWriter = new PdfWriter(invoiceId + ".pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            document.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA, BaseFont.CP1250));
            addTitlePage(document, invoiceId);
            addSellerAndBuyer(document, createBuyerInfo(orderDetail));
            addProductInfo(document, orderItems);
            addResume(document, orderDetail.getTotalPrice().toString());
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private void addTitlePage(Document document, UUID invoiceNumber) {
        Paragraph paragraph = new Paragraph("FAKTURA " + invoiceNumber)
                .setTextAlignment(TextAlignment.CENTER)
                .setBold()
                .setMarginBottom(30f);

        document.add(paragraph);
    }

    private void addSellerAndBuyer(Document document, String buyerInfo) {
        float[] colSize = {250f, 250f};
        Table table = new Table(colSize);

        table.addCell(new Cell()
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("SPRZEDAJACY").setFontColor(ColorConstants.RED)).setBold());
        table.addCell(new Cell()
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT)
                .add(new Paragraph("KUPUJACY").setFontColor(ColorConstants.RED)).setBold());

        table.addCell(new Cell()
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("""
                        Ski-shop
                        ul. Nadbystrzycka 38A
                        20-621 Lublin
                        """)));
        table.addCell(new Cell()
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph(buyerInfo)));
        document.add(table);
        document.add(new Paragraph("\n\n\n"));
    }

    private void addProductInfo(Document document, List<OrderItem> orderItems) {
        float[] colSize = {15f, 320f, 80f, 150f};
        Table table = new Table(colSize);

        table.addCell(new Cell()
                .add(new Paragraph("Lp").setBold()));
        table.addCell(new Cell()
                .add(new Paragraph("Nazwa").setBold()));
        table.addCell(new Cell()
                .add(new Paragraph("Ilosc").setBold()));
        table.addCell(new Cell()
                .add(new Paragraph("Cena za sztuke").setBold()));

        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem orderItem = orderItems.get(i);
            table.addCell(new Cell()
                    .setTextAlignment(TextAlignment.LEFT)
                    .add(new Paragraph(i + 1 + ".")));
            table.addCell(new Cell()
                    .setTextAlignment(TextAlignment.LEFT)
                    .add(new Paragraph(orderItem.getProduct().getBrand() + " " + orderItem.getProduct().getName())));
            table.addCell(new Cell()
                    .setTextAlignment(TextAlignment.RIGHT)
                    .add(new Paragraph(String.valueOf(orderItem.getQuantity()))));
            table.addCell(new Cell()
                    .setTextAlignment(TextAlignment.RIGHT)
                    .add(new Paragraph(orderItem.getUnitPrice().toString())));
        }

        document.add(table);
        document.add(new Paragraph("\n"));
    }

    private void addResume(Document document, String totalValue) {

        Paragraph paragraph = new Paragraph(new Text("Wartosc calkowita ").setBold())
                .setTextAlignment(TextAlignment.RIGHT);
        paragraph.add(totalValue + " zl");

        document.add(paragraph);

        paragraph = new Paragraph(new Text("Data wystawienia ").setBold())
                .setTextAlignment(TextAlignment.RIGHT);
        paragraph.add(new Timestamp(System.currentTimeMillis()).toString());

        document.add(paragraph);
    }

    private String createBuyerInfo(OrderDetail orderDetail) {
        StringBuilder sb = new StringBuilder();

        sb.append(orderDetail.getCustomer().getFirstName());
        sb.append(" ");
        sb.append(orderDetail.getCustomer().getLastName());
        sb.append("\n");
        sb.append(orderDetail.getAddress().getStreet());
        sb.append("\n");
        sb.append(orderDetail.getAddress().getZipCode());
        sb.append(" ");
        sb.append(orderDetail.getAddress().getCity());

        return sb.toString();
    }

}

