package tm.chmury.invoicegenerator.queue;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;
import tm.chmury.invoicegenerator.InvoiceGenerator;

@Slf4j
@Component
@AllArgsConstructor
public class Listener {

    private final InvoiceGenerator generator;

    @SqsListener(value = "${queue.first-queue}", deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    public void listenToSecondQueue(String message) {
        log.info("Received a message on a queue: {}", message);
        generator.generateInvoice(message);
    }


}
