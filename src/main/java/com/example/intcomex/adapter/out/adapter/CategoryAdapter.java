package com.example.intcomex.adapter.out.adapter;

import com.example.intcomex.adapter.out.model.CategoryModel;
import com.example.intcomex.adapter.out.repository.CategoryRepository;
import com.example.intcomex.domain.port.out.CategoryPort;
import com.example.intcomex.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class CategoryAdapter implements CategoryPort {

    private CategoryRepository repository;

    @Override
    public Mono<CategoryEntity> insert(CategoryEntity entity) {
        return Mono.just(entity)
                .map(CategoryModel::from)
                .flatMap(repository::save)
                .thenReturn(new CategoryEntity())
                .doOnSuccess(scss ->
                        log.info("Success CategoryAdapter.insert"))
                .doOnError(err ->
                        log.error("Error CategoryAdapter.insert: ", err));
    }

    @Override
    public Mono<CategoryEntity> findById(int id) {
        return repository.findById(id)
                .map(CategoryEntity::from)
                .doOnSuccess(scss ->
                        log.info("Success CategoryAdapter.findById"))
                .doOnError(err ->
                        log.error("Error CategoryAdapter.findById: ", err));
    }
}
