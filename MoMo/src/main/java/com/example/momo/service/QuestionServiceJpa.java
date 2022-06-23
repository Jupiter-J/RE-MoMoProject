package com.example.momo.service;

import com.example.momo.domain.category.CategoryRepository;
import com.example.momo.domain.question.QuestionRepository;
import com.example.momo.dto.category.CategoryDto;
import com.example.momo.dto.question.QuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;


@RequiredArgsConstructor
@Service
public class QuestionServiceJpa implements QuestionService{
    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public QuestionDto createQuestion(Long categoryId, QuestionDto dto) {
        return null;
    }

    @Override
    public Collection<QuestionDto> readAllQuestion(Long categoryId) {
        return null;
    }

    @Override
    public QuestionDto readQuestion(Long categoryId, Long questionId) {
        return null;
    }

    @Override
    public boolean updateQuestion(Long categoryId, Long questionId, QuestionDto dto) {
        return true;
    }


    @Override
    public boolean deleteQuestion(Long categoryId, Long questionId) {
        return true;
    }
}
