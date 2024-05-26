package com.naeshu.naeshubackend.comment.dto.request;

import java.time.LocalDate;

public record CommentRequest(String content,
                             LocalDate createdAt) {
}
