package com.example.momo.domain.question;

import com.example.momo.domain.category.CategoryEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @NotEmpty(message = "공백이 안됩니다")
    @Size( min = 2, max = 100, message = "2글자 이상 작성")
    private String title;
    private Date createAt;
    private Date updateAt;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = CategoryEntity.class
    )
    @JoinColumn(name = "category_Id")
    private CategoryEntity categoryEntity;




}
