package com.naeshu.naeshubackend.comment.dto.response;

import com.naeshu.naeshubackend.common.CommentStatus;
import java.time.LocalDate;

public record CommentResponse(String userName,
                              String content,
                              LocalDate createdAt,
                              CommentStatus commentStatus) {
}
