package com.example.momo.controller;

import com.example.momo.dto.question.QuestionDto;
import com.example.momo.service.QuestionService;
import com.example.momo.service.QuestionServiceJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController()
@RequestMapping("api/v1/categories/{categoryId}/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionServiceJpa questionServiceJpa;

    @PostMapping()
    public ResponseEntity<QuestionDto> createQuestion(@PathVariable("categoryId") Long category_id,
                                                      @RequestBody QuestionDto dto){
        QuestionDto result = this.questionServiceJpa.createQuestion(category_id, dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<Collection<QuestionDto>> readAllQuestion(@PathVariable("categoryId")Long category_id){
       Collection<QuestionDto> questionDtoList = this.questionServiceJpa.readAllQuestion(category_id);
        if (questionDtoList == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(questionDtoList);
    }

    @GetMapping("{categoryId}/questions/{questionId}")
    public ResponseEntity<QuestionDto> readQuestion(@PathVariable("categoryId")Long category_id,
                                                    @PathVariable("questionId")Long questionId){
        QuestionDto questionDto = this.questionServiceJpa.readQuestion(category_id, questionId);
        if (questionDto == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(questionDto);
    }

    @PutMapping("{categoryId}/questions/{questionId}")
    public ResponseEntity<?> updateQuestion(@PathVariable("categoryId") Long category_id,
                                            @PathVariable("questionId") Long questionId,
                                            @RequestBody QuestionDto dto){

        if (!questionServiceJpa.updateQuestion(category_id, questionId, dto))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();

    }

   // @DeleteMapping("{categoryId}/questions/{questionId}")


}
