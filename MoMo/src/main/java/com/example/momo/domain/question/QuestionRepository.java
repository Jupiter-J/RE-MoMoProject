package com.example.momo.domain.question;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface QuestionRepository extends CrudRepository<QuestionEntity,Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT question FROM question_entity WHERE question like %:keyword%"
    )
    List<QuestionSearch>searchQuestion(@RequestParam("keyword") String keyword);

    List<QuestionSearch> findByQuestionContains(@RequestParam("text") String text);

}



