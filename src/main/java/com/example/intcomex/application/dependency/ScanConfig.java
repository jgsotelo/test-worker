package com.example.intcomex.application.dependency;

import com.example.intcomex.adapter.out.adapter.CategoryAdapter;
import com.example.intcomex.adapter.out.adapter.ProductAdapter;
import com.example.intcomex.adapter.out.repository.CategoryRepository;
import com.example.intcomex.adapter.out.repository.ProductRepository;
import com.example.intcomex.domain.interactor.RegisterInteractor;
import com.example.intcomex.domain.interactor.SearchInteractor;
import com.example.intcomex.domain.port.in.RegisterUseCase;
import com.example.intcomex.domain.port.in.SearchUseCase;
import com.example.intcomex.domain.port.out.CategoryPort;
import com.example.intcomex.domain.port.out.ProductPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScanConfig {

    @Bean
    public RegisterUseCase registerUseCase(CategoryPort categoryPort,
                                           ProductPort productPort) {
        return RegisterInteractor.of(categoryPort, productPort);
    }

    @Bean
    public SearchUseCase searchUseCase(ProductPort productPort,
                                       CategoryPort categoryPort) {
        return SearchInteractor.of(productPort, categoryPort);
    }

    @Bean
    public CategoryPort categoryPort(CategoryRepository categoryRepository) {
        return CategoryAdapter.of(categoryRepository);
    }

    @Bean
    public ProductPort mproductPort(ProductRepository productRepository) {
        return ProductAdapter.of(productRepository);
    }
}
