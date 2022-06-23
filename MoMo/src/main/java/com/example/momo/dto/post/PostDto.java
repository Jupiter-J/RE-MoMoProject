package com.example.momo.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long post_id;
    private String content;
    private Date createAt;
    private Date updateAt;

    private boolean post_status;
    private boolean is_delete;




}
