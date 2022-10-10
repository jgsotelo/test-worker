package com.example.intcomex.adapter;

import com.example.intcomex.DataMock;
import com.example.intcomex.adapter.out.adapter.CategoryAdapter;
import com.example.intcomex.adapter.out.model.CategoryModel;
import com.example.intcomex.adapter.out.repository.CategoryRepository;
import com.example.intcomex.entity.CategoryEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class CategoryAdapterTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryAdapter adapter;

    @Test
    @DisplayName("test insert category")
    void insertWhenSuccess() {

        Mockito.when(repository.save(Mockito.any()))
                .thenReturn(Mono.just(DataMock.categoryModelMock()));

        Mono<CategoryEntity> categoryEntityMono = adapter.insert(DataMock.categoryEntityMock());

        StepVerifier.create(categoryEntityMono)
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("test insert category error integration")
    void insertWhenError() {

        Mockito.when(repository.save(Mockito.any()))
                .thenReturn(Mono.error(new Exception("err")));

        Mono<CategoryEntity> categoryEntityMono = adapter.insert(DataMock.categoryEntityMock());

        StepVerifier.create(categoryEntityMono)
                .expectError()
                .verify();
    }

    @Test
    @DisplayName("test find by id category")
    void findByIdWhenSuccess() {

        Mockito.when(repository.findById(Mockito.anyInt()))
                .thenReturn(Mono.just(DataMock.categoryModelMock()));

        Mono<CategoryEntity> categoryEntityMono = adapter.findById(1);

        StepVerifier.create(categoryEntityMono)
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("test find by id category error integration")
    void findByIdWhenError() {

        Mockito.when(repository.findById(1))
                .thenReturn(Mono.error(new Exception("err")));

        Mono<CategoryEntity> categoryEntityMono = adapter.findById(1);

        StepVerifier.create(categoryEntityMono)
                .expectError()
                .verify();
    }
}
