package com.example.intcomex.domain.port.in;

import com.example.intcomex.entity.CategoryEntity;
import com.example.intcomex.entity.ProductEntity;
import reactor.core.publisher.Mono;

public interface RegisterUseCase {

    Mono<Void> registerCategory(CategoryEntity entity);
    Mono<Void> registerProduct(ProductEntity entity);
    Mono<Void> registerProductMassive();
}
