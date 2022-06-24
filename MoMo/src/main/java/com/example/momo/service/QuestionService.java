package com.example.momo.service;

import com.example.momo.domain.question.QuestionEntity;
import com.example.momo.domain.question.QuestionSearch;
import com.example.momo.dto.category.CategoryDto;
import com.example.momo.dto.question.QuestionDto;

import java.util.Collection;

public interface QuestionService {

    QuestionDto createQuestion(Long categoryId, QuestionDto dto);
    Collection<QuestionDto> readAllQuestion(Long categoryId);
    QuestionDto readQuestion(Long categoryId, Long questionId);
    boolean updateQuestion(Long categoryId, Long questionId,QuestionDto dto);
    boolean deleteQuestion(Long categoryId, Long questionId);

    Collection<QuestionSearch>searchQuestion(String keyword);
    Collection<QuestionSearch>findByQuestionContaining(String text);



}
