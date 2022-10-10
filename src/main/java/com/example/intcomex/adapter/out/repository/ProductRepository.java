package com.example.intcomex.adapter.out.repository;

import com.example.intcomex.adapter.out.model.ProductModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveSortingRepository<ProductModel, Integer> {

    Flux<ProductModel> findAllBy(Pageable pageable);
}
