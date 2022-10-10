package com.example.intcomex.adapter.out.repository;

import com.example.intcomex.adapter.out.model.CategoryModel;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface CategoryRepository extends ReactiveSortingRepository<CategoryModel, Integer> {
}
