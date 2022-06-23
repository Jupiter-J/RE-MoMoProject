package com.example.momo.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionDto {

    private Long questionId;
    private String title;
    private Date createAt;
    private Date updateAt;
    private Long categoryId;

}
