package com.example.intcomex.entity;

import com.example.intcomex.adapter.in.model.ProductRequest;
import com.example.intcomex.adapter.out.model.ProductModel;
import com.example.intcomex.application.shared.Constants;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    private int productId;
    private String productName;
    private int supplierId;
    private CategoryEntity category;
    private BigDecimal quantityPerUnit;
    private BigDecimal unitsInStock;
    private BigDecimal unitPrice;
    private BigDecimal unitsOnOrder;
    private int reorderLevel;
    private boolean discontinued;

    public static ProductEntity from(final ProductRequest request) {
        return ProductEntity.builder()
                .productName(request.getName())
                .supplierId(request.getSupplier())
                .category(CategoryEntity.builder()
                        .categoryId(request.getCategory())
                        .build())
                .quantityPerUnit(request.getQuantityPerUnit())
                .unitsInStock(request.getUnitsInStock())
                .unitPrice(request.getUnitPrice())
                .unitsOnOrder(request.getUnitsOnOrder())
                .reorderLevel(Constants.DEFAULT_ORDER_LEVEL)
                .discontinued(request.isDiscontinued())
                .build();
    }

    public static ProductEntity from(final ProductModel model) {
        return ProductEntity.builder()
                .productId(model.getProductId())
                .productName(model.getProductName())
                .supplierId(model.getSupplierId())
                .category(CategoryEntity.builder()
                        .categoryId(model.getCategoryId())
                        .build())
                .quantityPerUnit(model.getQuantityPerUnit())
                .unitsInStock(model.getUnitsInStock())
                .unitPrice(model.getUnitPrice())
                .unitsOnOrder(model.getUnitsOnOrder())
                .reorderLevel(model.getReorderLevel())
                .discontinued(model.getDiscontinued() == Constants.PRODUCT_DISCONTINUED)
                .build();
    }
}
