package com.example.momo.domain.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
@AllArgsConstructor
@Data
public class PostEntity {

    @Id
    private Long post_id;

    private String content;
    private Date createAt;
    private Date updateAt;

    @Builder.Default
    private boolean post_status = false;
    @Builder.Default
    private boolean is_delete = false;


    public PostEntity() {

    }
}
