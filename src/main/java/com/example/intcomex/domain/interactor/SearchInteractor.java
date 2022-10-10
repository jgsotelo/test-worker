package com.example.intcomex.domain.interactor;

import com.example.intcomex.application.shared.Constants;
import com.example.intcomex.domain.port.in.SearchUseCase;
import com.example.intcomex.domain.port.out.CategoryPort;
import com.example.intcomex.domain.port.out.ProductPort;
import com.example.intcomex.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class SearchInteractor implements SearchUseCase {

    private ProductPort productPort;
    private CategoryPort categoryPort;

    @Override
    public Flux<ProductEntity> findAllProduct(String page, String size) {
        return productPort
                .findAll(defaultValue(page, Constants.DEFAULT_PAGE),
                        defaultValue(size, Constants.DEFAULT_ROW))
                .doOnComplete(() ->
                        log.info("Success SearchInteractor.findAll"))
                .doOnError(err ->
                        log.error("Error SearchInteractor.findAll: ", err));
    }

    @Override
    public Mono<ProductEntity> findByIdProduct(int id) {
        return productPort.findById(id)
                .flatMap(product -> categoryPort
                        .findById(product.getCategory().getCategoryId())
                        .map(category -> product.toBuilder()
                                .category(category)
                                .build()))
                .doOnSuccess(scc ->
                        log.info("Success SearchInteractor.findByIdProduct"))
                .doOnError(err ->
                        log.error("Error SearchInteractor.findByIdProduct: ", err));
    }

    private int defaultValue(String value, int defaultValue) {
        try {
            int newValue = Integer.parseInt(value);

            if (newValue <= 0) {
                return defaultValue;
            }

            return newValue;
        } catch (Exception ex) {
            return defaultValue;
        }
    }
}
