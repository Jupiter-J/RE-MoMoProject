package com.example.momo.domain.post;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "PostEntity")
public class PostEntity {

    @Id
    private Long post_id;
    private String content;



}
