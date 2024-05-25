package com.naeshu.naeshubackend.opinion.dto.request;

import java.time.LocalDate;

public record OpinionRequest(
    String content,
    LocalDate createdAt,
    Long announceNumber,
    Long userNumber
) {
}
