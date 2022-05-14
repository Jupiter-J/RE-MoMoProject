package dev.sideproject.momo.controller;

import dev.sideproject.momo.dto.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/post/{postId}/comment")
public class CommentController {



    @PostMapping()
    public ResponseEntity<CommentDto> createComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentDto dto){


        return null;
    }


    //테스트 확인용
    @GetMapping()
    public ResponseEntity<Collection<CommentDto>> readCommentAll(
            @PathVariable("postId") Long postId){

        return null;
    }



    @PutMapping("{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentDto dto,
            @PathVariable("commetId") Long commentId){


        return null;
    }


    @DeleteMapping("{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentDto dto,
            @PathVariable("commetId") Long commentId){


        return null;
    }




}
