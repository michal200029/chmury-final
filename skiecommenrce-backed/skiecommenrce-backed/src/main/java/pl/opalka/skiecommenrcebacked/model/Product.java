package pl.opalka.skiecommenrcebacked.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    private Long id;

    private String brand;

    private String name;

    private String condition;

    private BigDecimal unitPrice;

    private int unitsInStock;

    private String special;

    private String imageUrl;

    private double size;

    private String productKey;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private ProductCategory category;
}
