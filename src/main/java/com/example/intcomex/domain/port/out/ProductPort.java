package com.example.intcomex.domain.port.out;

import com.example.intcomex.adapter.out.model.ProductModel;
import com.example.intcomex.entity.ProductEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductPort {

    Mono<ProductEntity> insert(ProductEntity entity);
    Mono<Void> insertAll(List<ProductEntity> entities);
    Flux<ProductEntity> findAll(int page, int size);
    Mono<ProductEntity> findById(int id);
}
