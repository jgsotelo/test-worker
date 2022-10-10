package com.example.intcomex.adapter.in.model;

import com.example.intcomex.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Base64;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse implements Serializable {

    private int id;
    private String name;
    private int supplierId;
    private BigDecimal quantityPerUnit;
    private BigDecimal unitsInStock;
    private BigDecimal unitPrice;
    private BigDecimal unitsOnOrder;
    private int reorderLevel;
    private boolean discontinued;
    private int categoryId;
    private String categoryName;
    private String categoryPicture;

    public static ProductResponse from(ProductEntity entity) {
        return ProductResponse.builder()
                .id(entity.getProductId())
                .name(entity.getProductName())
                .supplierId(entity.getSupplierId())
                .categoryId(entity.getCategory().getCategoryId())
                .categoryName(entity.getCategory().getCategoryName())
                .categoryPicture(Base64.getEncoder()
                        .encodeToString(entity.getCategory().getPicture()))
                .quantityPerUnit(entity.getQuantityPerUnit())
                .unitsInStock(entity.getUnitsInStock())
                .unitPrice(entity.getUnitPrice())
                .unitsOnOrder(entity.getUnitsOnOrder())
                .reorderLevel(entity.getReorderLevel())
                .discontinued(entity.isDiscontinued())
                .build();
    }
}
