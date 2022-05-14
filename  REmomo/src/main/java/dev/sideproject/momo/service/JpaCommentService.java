package dev.sideproject.momo.service;

import dev.sideproject.momo.domain.post.PostRepository;
import dev.sideproject.momo.domain.user.UserRepository;
import dev.sideproject.momo.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class JpaCommentService implements CommentService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;



    @Override
    public CommentDto create(Long postId, CommentDto dto) {
        return null;
    }

    @Override
    public Collection<CommentDto> readAll(Long postId) {
        return null;
    }

    @Override
    public boolean update(Long postId, CommentDto dto, Long commentId) {
        return false;
    }

    @Override
    public boolean delete(Long postId, CommentDto dto, Long commentId) {
        return false;
    }



}
