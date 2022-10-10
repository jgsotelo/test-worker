package com.example.intcomex.adapter.out.model;

import com.example.intcomex.entity.ProductEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("product")
public class ProductModel implements Serializable {

    @Id
    @Column("productId")
    private int productId;

    @Column("productName")
    private String productName;

    @Column("supplierId")
    private int supplierId;

    @Column("categoryId")
    private int categoryId;

    @Column("quantityPerUnit")
    private BigDecimal quantityPerUnit;

    @Column("unitsInStock")
    private BigDecimal unitsInStock;

    @Column("unitPrice")
    private BigDecimal unitPrice;

    @Column("unitsOnOrder")
    private BigDecimal unitsOnOrder;

    @Column("reorderLevel")
    private int reorderLevel;

    @Column("discontinued")
    private int discontinued;

    public static ProductModel from(ProductEntity entity) {
        return ProductModel.builder()
                .productId(entity.getProductId())
                .productName(entity.getProductName())
                .supplierId(entity.getSupplierId())
                .categoryId(entity.getCategory().getCategoryId())
                .quantityPerUnit(entity.getQuantityPerUnit())
                .unitsInStock(entity.getUnitsInStock())
                .unitPrice(entity.getUnitPrice())
                .unitsOnOrder(entity.getUnitsOnOrder())
                .reorderLevel(entity.getReorderLevel())
                .discontinued((entity.isDiscontinued()) ? 1 : 0)
                .build();
    }
}
