package com.example.momo.controller;

import com.example.momo.domain.question.QuestionEntity;
import com.example.momo.domain.question.QuestionSearch;
import com.example.momo.dto.question.QuestionDto;
import com.example.momo.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/searches")
@RequiredArgsConstructor
public class SearchController {
    private final QuestionService questionService;
    private final Logger logger = LoggerFactory.getLogger(SearchController.class);


    @GetMapping("")
    public ResponseEntity<Collection<QuestionSearch>> searchQuestion(@RequestParam("keyword") String keyword){
        Collection<QuestionSearch> result = this.questionService.searchQuestion(keyword);
        logger.info("keyword 검색: {}",keyword);
        return ResponseEntity.ok(result);
    }


    @GetMapping("questions/test")
    public ResponseEntity<Collection<QuestionSearch>> findByQuestionContaining(@RequestParam("text") String text){
        Collection<QuestionSearch> result = this.questionService.findByQuestionContaining(text);
        logger.info("keyword 검색: {}",text);
        return ResponseEntity.ok(result);
    }
}
