package com.example.intcomex.domain.interactor;

import com.example.intcomex.domain.port.in.RegisterUseCase;
import com.example.intcomex.domain.port.out.CategoryPort;
import com.example.intcomex.domain.port.out.ProductPort;
import com.example.intcomex.entity.CategoryEntity;
import com.example.intcomex.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.stream.IntStream;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class RegisterInteractor implements RegisterUseCase {

    private CategoryPort categoryPort;
    private ProductPort productPort;

    @Override
    public Mono<Void> registerCategory(CategoryEntity entity) {
        return categoryPort
                .insert(entity)
                .then()
                .doOnSuccess(scss ->
                        log.info("Success RegisterInteractor.registerCategory"))
                .doOnError(err ->
                        log.error("Error RegisterInteractor.registerCategory: ", err));
    }

    @Override
    public Mono<Void> registerProduct(ProductEntity entity) {
        return productPort
                .insert(entity)
                .then()
                .doOnSuccess(scss ->
                        log.info("Success RegisterInteractor.registerProduct"))
                .doOnError(err ->
                        log.error("Error RegisterInteractor.registerProduct: ", err));
    }

    @Override
    public Mono<Void> registerProductMassive() {
        return Flux
                .range(1, 100000)
                .map(i -> ProductEntity.builder()
                        .productName("LAPTOP DELL "
                                .concat(String.valueOf(LocalTime.now()
                                        .getSecond()))
                                .concat(String.valueOf(i)))
                        .supplierId(1)
                        .category(CategoryEntity.builder()
                                .categoryId(1).build())
                        .quantityPerUnit(BigDecimal.ZERO)
                        .unitsInStock((BigDecimal.ZERO))
                        .unitPrice((BigDecimal.valueOf(LocalTime.now()
                                .getSecond())))
                        .unitsOnOrder((BigDecimal.ZERO))
                        .build())
                .collectList()
                .flatMap(productPort::insertAll)
                .then()
                .doOnSuccess(scss ->
                        log.info("Success RegisterInteractor.registerProductMassive"))
                .doOnError(err ->
                        log.error("Error RegisterInteractor.registerProductMassive: ", err));
    }
}
