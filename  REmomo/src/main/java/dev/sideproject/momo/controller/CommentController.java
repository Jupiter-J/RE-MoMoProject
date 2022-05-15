package dev.sideproject.momo.controller;

import dev.sideproject.momo.dto.CommentDto;
import dev.sideproject.momo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/post/{postId}/comment")
public class CommentController {

    private final CommentService commentService;


    @PostMapping()
    public ResponseEntity<CommentDto> createComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentDto dto){

        CommentDto result = this.commentService.create(postId, dto);
        return ResponseEntity.ok(result);
    }


    //테스트 확인용
    @GetMapping()
    public ResponseEntity<Collection<CommentDto>> readCommentAll(
            @PathVariable("postId") Long postId){
        Collection<CommentDto> commentDtoList = this.commentService.readAll(postId);
        if (commentDtoList == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(commentDtoList);

    }



    @PutMapping("{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentDto dto,
            @PathVariable("commetId") Long commentId){

        if (!commentService.update(postId, dto, commentId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentDto dto,
            @PathVariable("commetId") Long commentId){

        if (!commentService.delete(postId, dto, commentId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }




}
