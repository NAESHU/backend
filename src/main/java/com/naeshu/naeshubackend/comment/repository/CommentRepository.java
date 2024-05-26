package com.naeshu.naeshubackend.comment.repository;

import com.naeshu.naeshubackend.comment.Comment;
import com.naeshu.naeshubackend.comment.dto.response.CommentResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<CommentResponse> findAllById(Long opinionId);
}
