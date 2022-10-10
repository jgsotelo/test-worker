package com.example.intcomex.domain;

import com.example.intcomex.DataMock;
import com.example.intcomex.domain.interactor.SearchInteractor;
import com.example.intcomex.domain.port.out.CategoryPort;
import com.example.intcomex.domain.port.out.ProductPort;
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
public class SearchInteractorTest {

    @Mock
    private ProductPort productPort;

    @Mock
    private CategoryPort categoryPort;

    @InjectMocks
    private SearchInteractor interactor;

    @Test
    @DisplayName("test find all product")
    void findAllProductWhenSuccess() {
        Mockito.when(productPort.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(Flux.just(DataMock.productEntityMock()));

        Flux<ProductEntity> productEntityFlux = interactor.findAllProduct("1", "10");

        StepVerifier.create(productEntityFlux)
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("test find all product error integration")
    void findAllProductWhenError() {
        Mockito.when(productPort.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(Flux.error(new Exception("err")));

        Flux<ProductEntity> productEntityFlux = interactor.findAllProduct("1", "10");

        StepVerifier.create(productEntityFlux)
                .expectError()
                .verify();
    }

    @Test
    @DisplayName("test find by id product")
    void findByIdProductWhenSuccess() {
        Mockito.when(productPort.findById(Mockito.anyInt()))
                .thenReturn(Mono.just(DataMock.productEntityMock()));

        Mockito.when(categoryPort.findById(Mockito.anyInt()))
                .thenReturn(Mono.just(DataMock.categoryEntityMock()));

        Mono<ProductEntity> productEntityFlux = interactor.findByIdProduct(1);

        StepVerifier.create(productEntityFlux)
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("test find by id product error integration product")
    void findByIdProductWhenError() {
        Mockito.when(productPort.findById(Mockito.anyInt()))
                .thenReturn(Mono.error(new Exception("err")));

        Mono<ProductEntity> productEntityFlux = interactor.findByIdProduct(1);

        StepVerifier.create(productEntityFlux)
                .expectError()
                .verify();
    }

    @Test
    @DisplayName("test find by id product error integration category")
    void findByIdProductWhenErrorCategory() {
        Mockito.when(productPort.findById(Mockito.anyInt()))
                .thenReturn(Mono.just(DataMock.productEntityMock()));

        Mockito.when(categoryPort.findById(Mockito.anyInt()))
                .thenReturn(Mono.error(new Exception("err")));

        Mono<ProductEntity> productEntityFlux = interactor.findByIdProduct(1);

        StepVerifier.create(productEntityFlux)
                .expectError()
                .verify();
    }
}
