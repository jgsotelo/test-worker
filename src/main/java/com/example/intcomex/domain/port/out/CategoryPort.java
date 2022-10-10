package com.example.intcomex.domain.port.out;

import com.example.intcomex.adapter.out.model.CategoryModel;
import com.example.intcomex.entity.CategoryEntity;
import reactor.core.publisher.Mono;

public interface CategoryPort {

    Mono<CategoryEntity> insert(CategoryEntity entity);
    Mono<CategoryEntity> findById(int id);
}
