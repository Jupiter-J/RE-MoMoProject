package com.example.momo.service;

import com.example.momo.domain.category.CategoryEntity;
import com.example.momo.domain.category.CategoryRepository;
import com.example.momo.domain.question.QuestionEntity;
import com.example.momo.domain.question.QuestionRepository;
import com.example.momo.domain.question.QuestionSearch;
import com.example.momo.dto.category.CategoryDto;
import com.example.momo.dto.question.QuestionDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class QuestionServiceJpa implements QuestionService{
    private final Logger logger = LoggerFactory.getLogger(QuestionServiceJpa.class);
    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public QuestionDto createQuestion(Long categoryId, QuestionDto dto) {
        //각각 레파지토리에 있는지 확인
        if (!categoryRepository.existsById(categoryId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //값을 찾아서 저장한다
        CategoryEntity categoryEntity = this.categoryRepository.findById(categoryId).get();


        //바뀐 값을 객체에 저장한다
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestion(dto.getQuestion());
        questionEntity.setCategoryEntity(categoryRepository.findById(categoryId).get());


        /**
         2차 배열 형태로 값을 불러오도록 하기
         */
        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(categoryEntity.getCategoryId())
                .category(categoryEntity.getCategory())
                .build();

        //레파지토리에 최종 저장!!
        questionEntity = this.questionRepository.save(questionEntity);

        return new QuestionDto(
                questionEntity.getQuestionId(),
                questionEntity.getQuestion(),
                questionEntity.getCreateAt(),
                questionEntity.getUpdateAt(),
                categoryDto
        );
    }

    @Override
    public Collection<QuestionDto> readAllQuestion(Long categoryId) {
        //들어온값 확인하기
        Optional<CategoryEntity> categoryEntityOptional = this.categoryRepository.findById(categoryId);
        //빈집에 집어넣기
        CategoryEntity categoryEntity = categoryEntityOptional.get();

        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(categoryEntity.getCategoryId())
                .category(categoryEntity.getCategory())
                .build();

        //본론실행
        List<QuestionDto> questionDtoList = new ArrayList<>();
        this.questionRepository.findAll().forEach(
                questionEntity -> questionDtoList.add(
                        new QuestionDto(
                                questionEntity.getQuestionId(),
                                questionEntity.getQuestion(),
                                questionEntity.getCreateAt(),
                                questionEntity.getUpdateAt(),
                                categoryDto
                        )));
        return questionDtoList;
    }

    @Override
    public QuestionDto readQuestion(Long categoryId, Long questionId) {

        //들어온값 확인하기
        if (!this.categoryRepository.existsById(categoryId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if (!this.questionRepository.existsById(questionId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        QuestionEntity questionEntity = this.questionRepository.findById(questionId).get();

        CategoryEntity categoryEntity =  this.categoryRepository.findById(categoryId).get();
        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(categoryEntity.getCategoryId())
                .category(categoryEntity.getCategory())
                .build();

        return new QuestionDto(
                questionEntity.getQuestionId(),
                questionEntity.getQuestion(),
                questionEntity.getCreateAt(),
                questionEntity.getUpdateAt(),
                categoryDto
        );
    }

    @Override
    public boolean updateQuestion(Long categoryId, Long questionId, QuestionDto dto) {

        //카테고리 유무확인
        if (!categoryRepository.existsById(categoryId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //카테고리 번호 확인
        QuestionEntity questionEntity = this.questionRepository.findById(questionId).get();
        if (!questionEntity.getCategoryEntity().getCategoryId().equals(categoryId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        //본론
        questionEntity.setQuestion(
                dto.getQuestion()==null? questionEntity.getQuestion() : dto.getQuestion());

        //저장
        this.questionRepository.save(questionEntity);


        return true;
    }

    @Override
    public boolean deleteQuestion(Long categoryId, Long questionId) {
        if (!this.categoryRepository.existsById(categoryId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        QuestionEntity questionEntity = this.questionRepository.findById(questionId).get();
        if (!this.questionRepository.existsById(questionId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (!questionEntity.getCategoryEntity().getCategoryId().equals(categoryId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        this.questionRepository.deleteById(questionId);
        return true;
    }

    @Override
    public Collection<QuestionSearch> searchQuestion(String keyword) {
        List<QuestionSearch> questionSearchList = this.questionRepository.searchQuestion(keyword);
        logger.info("ServiceKeyword: {}",keyword);
        return questionSearchList;
    }

    @Override
    public Collection<QuestionSearch> findByQuestionContaining(String text) {
        logger.info("serviceText: {}",text);
        List<QuestionSearch> questionSearchLists = this.questionRepository.findByQuestionContains(text);
        return questionSearchLists;
    }
}
