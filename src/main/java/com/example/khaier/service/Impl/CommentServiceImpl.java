package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.CommentRequestDto;
import com.example.khaier.dto.response.CommentResponseDto;
import com.example.khaier.entity.Comment;
import com.example.khaier.entity.User;
import com.example.khaier.helper.PostHelper;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.CommentRequestDtoToCommentMapper;
import com.example.khaier.mapper.CommentToCommentResponseDtoMapper;
import com.example.khaier.repository.CommentRepository;
import com.example.khaier.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserHelper userHelper;
    private final CommentRequestDtoToCommentMapper toCommentMapper;
    private final PostHelper postHelper;
    private final CommentToCommentResponseDtoMapper toCommentResponseDtoMapper;

    @Override
    public CommentResponseDto addComment(CommentRequestDto commentRequestDto){
        User authUser= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user=userHelper.findUserByIdOrThrowNotFoundException(authUser.getUserId());
        Comment comment=toCommentMapper.apply(commentRequestDto);
        comment.setUser(user);
        comment=commentRepository.save(comment);
        postHelper.findPostByIdOrThrowNotFound(commentRequestDto.postId()).getComments().add(comment);
        return  toCommentResponseDtoMapper.apply(comment);
    }
    @Override
    public CommentResponseDto getById(Long commentId){
        Comment comment=commentRepository.findById(commentId).orElseThrow(
                ()->new NotFoundException("Comment with id : "+commentId+" not found!")
        );
        return toCommentResponseDtoMapper.apply(comment);
    }

    @Override
    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        postHelper.findPostByIdOrThrowNotFound(postId);
        List<Comment>comments=commentRepository.findByPost_PostId(postId);
        return comments.stream().map(toCommentResponseDtoMapper).toList();
    }
}
