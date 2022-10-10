package com.example.intcomex.adapter.out.adapter;

import com.example.intcomex.adapter.out.model.CategoryModel;
import com.example.intcomex.adapter.out.model.ProductModel;
import com.example.intcomex.adapter.out.repository.ProductRepository;
import com.example.intcomex.domain.port.out.ProductPort;
import com.example.intcomex.entity.CategoryEntity;
import com.example.intcomex.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class ProductAdapter implements ProductPort {

    private ProductRepository repository;

    @Override
    public Mono<ProductEntity> insert(ProductEntity entity) {
        return Mono.just(entity)
                .map(ProductModel::from)
                .flatMap(repository::save)
                .thenReturn(new ProductEntity())
                .doOnSuccess(scss ->
                        log.info("Success ProductAdapter.insert"))
                .doOnError(err ->
                        log.error("Error ProductAdapter.insert: ", err));
    }

    @Override
    public Mono<Void> insertAll(List<ProductEntity> entities) {
        return Flux.fromIterable(entities)
                .map(ProductModel::from)
                .flatMap(repository::save)
                .then()
                .doOnSuccess(scss ->
                        log.info("Success ProductAdapter.insertAll"))
                .doOnError(err ->
                        log.error("Error ProductAdapter.insertAll: ", err));
    }

    @Override
    public Flux<ProductEntity> findAll(int page, int size) {
        return repository
                .findAllBy(PageRequest.of(page,size))
                .map(ProductEntity::from)
                .doOnComplete(() ->
                        log.info("Success ProductAdapter.findAll"))
                .doOnError(err ->
                        log.error("Error ProductAdapter.findAll: ", err));
    }

    @Override
    public Mono<ProductEntity> findById(int id) {
        return repository.findById(id)
                .map(ProductEntity::from)
                .doOnSuccess(scc ->
                        log.info("Success ProductAdapter.findById"))
                .doOnError(err ->
                        log.error("Error ProductAdapter.findById: ", err));
    }
}
