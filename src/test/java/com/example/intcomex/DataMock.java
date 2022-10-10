package com.example.intcomex;

import com.example.intcomex.adapter.out.model.CategoryModel;
import com.example.intcomex.adapter.out.model.ProductModel;
import com.example.intcomex.entity.CategoryEntity;
import com.example.intcomex.entity.ProductEntity;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

public class DataMock {

    public static CategoryModel categoryModelMock() {
        return CategoryModel.builder()
                .categoryId(1)
                .categoryName("423423")
                .description(StringUtils.EMPTY)
                .build();
    }

    public static CategoryEntity categoryEntityMock() {
        return CategoryEntity.builder()
                .categoryId(1)
                .categoryName("Category 1")
                .picture("23423".getBytes(StandardCharsets.UTF_8))
                .build();
    }

    public static ProductModel productModelMock() {
        return ProductModel.builder()
                .productId(1)
                .productName("aa")
                .discontinued(1)
                .categoryId(1)
                .build();
    }

    public static ProductEntity productEntityMock() {
        return ProductEntity.builder()
                .productId(1)
                .productName("aa")
                .discontinued(false)
                .category(CategoryEntity.builder()
                        .categoryId(1)
                        .build())
                .build();
    }
}
