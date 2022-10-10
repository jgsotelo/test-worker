package com.example.intcomex.adapter.in.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest implements Serializable {

    @NotBlank(message = "{api.notBlank}")
    private String name;

    @Min(value = 1, message = "{api.product.supplier}")
    private int supplier;

    @Min(value = 1, message = "{api.product.category}")
    private int category;

    @Min(value = 0, message = "{api.min}")
    @NotNull(message = "{api.notNull}")
    private BigDecimal quantityPerUnit;

    @Min(value = 0, message = "{api.min}")
    @NotNull(message = "{api.notNull}")
    private BigDecimal unitsInStock;

    @Min(value = 0, message = "{api.min}")
    @NotNull(message = "{api.notNull}")
    private BigDecimal unitPrice;

    @Min(value = 0, message = "{api.min}")
    @NotNull(message = "{api.notNull}")
    private BigDecimal unitsOnOrder;

    private boolean discontinued;
}
