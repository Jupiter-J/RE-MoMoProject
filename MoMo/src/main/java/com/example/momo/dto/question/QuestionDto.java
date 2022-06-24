package com.example.momo.dto.question;

import com.example.momo.domain.category.CategoryEntity;
import com.example.momo.dto.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionDto {

    private Long questionId;
    private String question;
    private Instant createAt;
    private Instant updateAt;
    private CategoryDto categoryDto;

}
