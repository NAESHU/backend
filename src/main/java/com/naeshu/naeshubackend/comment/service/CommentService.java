package com.naeshu.naeshubackend.comment.service;

import com.naeshu.naeshubackend.comment.Comment;
import com.naeshu.naeshubackend.comment.dto.request.CommentRequest;
import com.naeshu.naeshubackend.comment.repository.CommentRepository;
import com.naeshu.naeshubackend.common.CommentStatus;
import com.naeshu.naeshubackend.opinion.Opinion;
import com.naeshu.naeshubackend.opinion.repository.OpinionRepository;
import com.naeshu.naeshubackend.user.User;
import com.naeshu.naeshubackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final OpinionRepository opinionRepository;
    private final UserRepository userRepository;

    public void create(Long userId, Long opinionId, CommentRequest commentRequest){
        User user =userRepository.findById(userId).get();
        Opinion opinion = opinionRepository.findById(opinionId).get();
        commentRepository.save(new Comment(commentRequest.content(), user, opinion, commentRequest.createdAt(), CommentStatus.NOT_SOLD));
    }

//    public List<CommentResponse> findAll(Long opinionId){
//        return commentRepository.findAllById(opinionId);
//    }

    public void sold(Long commentId){
        Comment comment = commentRepository.findById(commentId).get();
        comment.updateStatus(CommentStatus.SOLD);
        commentRepository.save(comment);
    }
}
