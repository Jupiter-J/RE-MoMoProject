package com.example.momo.domain.question;

import com.example.momo.domain.BaseEntity;
import com.example.momo.domain.category.CategoryEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "QuestionEntity")
public class QuestionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @NotEmpty(message = "공백이 안됩니다")
    @Size( min = 2, max = 100, message = "2글자 이상 작성")
    private String question;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = CategoryEntity.class
    )
    @JoinColumn(name = "category_Id")
    private CategoryEntity categoryEntity;




}
