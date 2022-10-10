package com.example.intcomex.adapter.in.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest implements Serializable {

    @NotBlank(message = "{api.notBlank}")
    private String name;

    @NotNull(message = "{api.notNull}")
    private String description;

    @NotBlank(message = "{api.notBlank}")
    private String picture;
}