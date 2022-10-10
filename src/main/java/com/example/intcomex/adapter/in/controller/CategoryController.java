package com.example.intcomex.adapter.in.controller;

import com.example.intcomex.adapter.in.model.CategoryRequest;
import com.example.intcomex.domain.port.in.RegisterUseCase;
import com.example.intcomex.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private RegisterUseCase registerUseCase;

    public CategoryController(RegisterUseCase registerUseCase) {
        this.registerUseCase = registerUseCase;
    }

    @PostMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> create(@Valid @RequestBody CategoryRequest request) {
        return registerUseCase
                .registerCategory(CategoryEntity.from(request));
    }
}
