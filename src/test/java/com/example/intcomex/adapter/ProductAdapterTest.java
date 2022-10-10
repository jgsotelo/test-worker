package com.example.intcomex.adapter;

import com.example.intcomex.DataMock;
import com.example.intcomex.adapter.out.adapter.ProductAdapter;
import com.example.intcomex.adapter.out.repository.ProductRepository;
import com.example.intcomex.entity.CategoryEntity;
import com.example.intcomex.entity.ProductEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ProductAdapterTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductAdapter adapter;

    @Test
    @DisplayName("test insert product")
    void insertWhenSuccess() {

        Mockito.when(repository.save(Mockito.any()))
                .thenReturn(Mono.just(DataMock.productModelMock()));

        Mono<ProductEntity> categoryEntityMono = adapter.insert(DataMock.productEntityMock());

        StepVerifier.create(categoryEntityMono)
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("test insert product error integration")
    void insertWhenError() {

        Mockito.when(repository.save(Mockito.any()))
                .thenReturn(Mono.error(new Exception("err")));

        Mono<ProductEntity> categoryEntityMono = adapter.insert(DataMock.productEntityMock());

        StepVerifier.create(categoryEntityMono)
                .expectError()
                .verify();
    }

    @Test
    @DisplayName("test find by id product")
    void findByIdWhenSuccess() {

        Mockito.when(repository.findById(Mockito.anyInt()))
                .thenReturn(Mono.just(DataMock.productModelMock()));

        Mono<ProductEntity> categoryEntityMono = adapter.findById(1);

        StepVerifier.create(categoryEntityMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    @DisplayName("test find by id product error integration")
    void findByIdWhenError() {

        Mockito.when(repository.findById(Mockito.anyInt()))
                .thenReturn(Mono.error(new Exception("err")));

        Mono<ProductEntity> categoryEntityMono = adapter.findById(1);

        StepVerifier.create(categoryEntityMono)
                .expectError()
                .verify();
    }
}
