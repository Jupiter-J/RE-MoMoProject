package com.example.momo.service;

import com.example.momo.domain.category.CategoryEntity;
import com.example.momo.domain.category.CategoryRepository;
import com.example.momo.dto.category.CategoryDto;
import com.example.momo.dto.response.ErrorCode;
import com.example.momo.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryServiceJpa implements CategoryService{
    private final Logger logger = LoggerFactory.getLogger(CategoryServiceJpa.class);
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategory(dto.getCategory());
        categoryEntity = this.categoryRepository.save(categoryEntity);

        return new CategoryDto(
                categoryEntity.getCategoryId(),
                categoryEntity.getCategory()
        );
    }

    @Override
    public Collection<CategoryDto> readAllCategory() {
        List<CategoryDto> categoryDtoList= new ArrayList<>();
        this.categoryRepository.findAll().forEach(categoryEntity ->
                categoryDtoList.add(
                        new CategoryDto(
                                categoryEntity.getCategoryId(),
                                categoryEntity.getCategory()
                        )
                ));

        return categoryDtoList;
    }

    @Override
    public CategoryDto readCategory(Long categoryId) {
        Optional<CategoryEntity> categoryEntityOptional = this.categoryRepository.findById(categoryId);
        if (categoryEntityOptional.isEmpty())
            throw new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY_EXCEPTION);
        CategoryEntity categoryEntity = categoryEntityOptional.get();

        return new CategoryDto(
                categoryEntity.getCategoryId(),
                categoryEntity.getCategory()
        );
    }

    @Override
    public boolean updateCategory(Long categoryId, CategoryDto dto) {
        Optional<CategoryEntity> categoryEntityOptional = this.categoryRepository.findById(categoryId);

        if (categoryEntityOptional.isEmpty())
            throw new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY_EXCEPTION);
        CategoryEntity categoryEntity = categoryEntityOptional.get();
        categoryEntity.setCategory(dto.getCategory());

        this.categoryRepository.save(categoryEntity);
        return true;
    }

    @Override
    public boolean deleteCategory(Long categoryId) {
        Optional<CategoryEntity> categoryEntityOptional = this.categoryRepository.findById(categoryId);
        if (categoryEntityOptional.isEmpty())
            throw new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY_EXCEPTION);
        CategoryEntity categoryEntity = categoryEntityOptional.get();
        this.categoryRepository.delete(categoryEntity);

        return true;
    }










}
