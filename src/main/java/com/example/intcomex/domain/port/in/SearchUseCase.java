package com.example.intcomex.domain.port.in;

import com.example.intcomex.entity.ProductEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SearchUseCase {

    Flux<ProductEntity> findAllProduct(String page, String size);
    Mono<ProductEntity> findByIdProduct(int id);
}
