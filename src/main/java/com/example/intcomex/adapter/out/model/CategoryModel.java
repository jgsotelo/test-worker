package com.example.intcomex.adapter.out.model;

import com.example.intcomex.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("category")
public class CategoryModel implements Serializable {

    @Id
    @Column("categoryId")
    private int categoryId;

    @Column("categoryName")
    private String categoryName;
    private String description;
    private byte[] picture;

    public static CategoryModel from(final CategoryEntity entity) {
        return CategoryModel.builder()
                .categoryId(entity.getCategoryId())
                .categoryName(entity.getCategoryName())
                .description(entity.getDescription())
                .picture(entity.getPicture())
                .build();
    }
}
