package dev.sideproject.momo.service;

import dev.sideproject.momo.domain.comment.CommentEntity;
import dev.sideproject.momo.domain.comment.CommentRepository;
import dev.sideproject.momo.domain.post.PostEntity;
import dev.sideproject.momo.domain.post.PostRepository;
import dev.sideproject.momo.domain.user.UserEntity;
import dev.sideproject.momo.domain.user.UserRepository;
import dev.sideproject.momo.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JpaCommentService implements CommentService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;



    @Override
    public CommentDto create(Long postId, CommentDto dto) {
        if (!this.postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (!this.userRepository.existsById(dto.getUserId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        PostEntity postEntity = this.postRepository.findById(postId).get();
        UserEntity userEntity = this.userRepository.findById(dto.getUserId()).get();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(dto.getComment());

        commentEntity.setWriter(userEntity);
        commentEntity.setPostEntity(postEntity);
        commentEntity = this.commentRepository.save(commentEntity);

        return new CommentDto(
                commentEntity.getCid(),
                commentEntity.getComment(),
                commentEntity.getCreateAt(),
                commentEntity.getPostEntity().getPid(),
                commentEntity.getWriter().getUid()
        );
    }

    @Override
    public Collection<CommentDto> readAll(Long postId) {
        Optional<PostEntity> postEntityOptional = this.postRepository.findById(postId);

        List<CommentDto> commentDtoList = new ArrayList<>();
        this.commentRepository.findAll().forEach(
                commentEntity -> commentDtoList.add(
                        new CommentDto(
                                commentEntity.getCid(),
                                commentEntity.getComment(),
                                commentEntity.getCreateAt(),
                                commentEntity.getWriter().getUid(),
                                commentEntity.getPostEntity().getPid()
                        )
                )
        );

        return commentDtoList;
    }

    @Override
    public boolean update(Long postId, CommentDto dto, Long commentId) {

        if (!this.commentRepository.existsById(commentId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity commentEntity = this.commentRepository.findById(commentId).get();

        if (!commentEntity.getPostEntity().getPid().equals(postId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (!commentEntity.getWriter().getUid().equals(dto.getUserId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        commentEntity.setComment(
                dto.getComment() == null ? commentEntity.getComment() : dto.getComment());
        this.commentRepository.save(commentEntity);

        return true;
    }

    @Override
    public boolean delete(Long postId, CommentDto dto, Long commentId) {

        if (!this.commentRepository.existsById(commentId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity commentEntity = this.commentRepository.findById(commentId).get();
        if (!commentEntity.getPostEntity().getPid().equals(postId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        this.commentRepository.deleteById(commentId);

        return true;
    }



}
