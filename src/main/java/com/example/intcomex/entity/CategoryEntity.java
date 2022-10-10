package com.example.intcomex.entity;

import com.example.intcomex.adapter.in.model.CategoryRequest;
import com.example.intcomex.adapter.out.model.CategoryModel;
import lombok.*;

import java.util.Base64;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryEntity {

    private int categoryId;
    private String categoryName;
    private String description;
    private byte[] picture;

    public static CategoryEntity from(final CategoryRequest request) {
        return CategoryEntity.builder()
                .categoryName(request.getName())
                .description(request.getDescription())
                .picture(Base64.getDecoder()
                        .decode(request.getPicture()))
                .build();
    }

    public static CategoryEntity from(final CategoryModel model) {
        return CategoryEntity.builder()
                .categoryId(model.getCategoryId())
                .categoryName(model.getCategoryName())
                .picture(model.getPicture())
                .build();
    }
}
