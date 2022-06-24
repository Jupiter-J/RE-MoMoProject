package com.example.momo.domain.category;

import com.example.momo.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "CategoryEntity")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @NotEmpty(message = "공백이 안됩니다")
    @Size( max = 20, message = "20글자 이하만 가능")
    private String category;

}
