package com.example.intcomex.adapter.in.controller;

import com.example.intcomex.adapter.in.model.ProductRequest;
import com.example.intcomex.adapter.in.model.ProductResponse;
import com.example.intcomex.application.shared.Constants;
import com.example.intcomex.domain.port.in.RegisterUseCase;
import com.example.intcomex.domain.port.in.SearchUseCase;
import com.example.intcomex.entity.ProductEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/product")
public class ProductController {

    private RegisterUseCase registerUseCase;
    private SearchUseCase searchUseCase;

    public ProductController(RegisterUseCase registerUseCase, SearchUseCase searchUseCase) {
        this.registerUseCase = registerUseCase;
        this.searchUseCase = searchUseCase;
    }

    @PostMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> create(@Valid @RequestBody ProductRequest request) {
        return registerUseCase
                .registerProduct(ProductEntity.from(request));
    }

    @PostMapping(value = "/massive",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> massive() {
        return registerUseCase.registerProductMassive();
    }

    @GetMapping(value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductResponse> findAll(@RequestHeader HttpHeaders headers) {
        return searchUseCase
                .findAllProduct(Objects.requireNonNull(headers.getFirst(Constants.X_PAGE)),
                        Objects.requireNonNull(headers.getFirst(Constants.X_PER_PAGE)))
                .map(ProductResponse::from);
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProductResponse> findById(@PathVariable int id) {
        return searchUseCase.findByIdProduct(id)
                .map(ProductResponse::from);
    }
}
