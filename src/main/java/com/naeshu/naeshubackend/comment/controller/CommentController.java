package com.naeshu.naeshubackend.comment.controller;

import com.naeshu.naeshubackend.auth.Auth;
import com.naeshu.naeshubackend.auth.AuthInfo;
import com.naeshu.naeshubackend.comment.dto.request.CommentRequest;
import com.naeshu.naeshubackend.comment.dto.response.CommentResponse;
import com.naeshu.naeshubackend.comment.service.CommentService;
import com.naeshu.naeshubackend.common.UnAuthorizedException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/naeshu/opinion/{opinionId}")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/register")
    public ResponseEntity<Void> create(@PathVariable Long opinionId,
                                       @Auth AuthInfo authInfo,
                                       @RequestBody CommentRequest commentRequest){
        verifyRole(authInfo);
        commentService.create(authInfo.memberId(), opinionId, commentRequest);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/comments")
//    public ResponseEntity<List<CommentResponse>> findAll(@PathVariable Long opinionId){
//        List<CommentResponse> commentResponseList = commentService.findAll(opinionId);
//        return ResponseEntity.ok(commentResponseList);
//    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<Void> sold(@PathVariable Long commentId, @PathVariable Long opinionId){
        commentService.sold(commentId);
        return ResponseEntity.noContent().build();
    }

    private void verifyRole(AuthInfo authInfo) {
        if (!authInfo.role().equals("USER")) {
            throw new UnAuthorizedException("USER 권한이 아닙니다.");
        }
    }
}
