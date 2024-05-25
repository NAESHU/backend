package com.naeshu.naeshubackend.announcement;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AnnouncementCreateRequest {

    private String title;
    private String content;
    private Long opinionPrice;
    private Long commentPrice;
    private @JsonFormat(pattern = "yyyy-MM-dd") LocalDate deadline;

    public AnnouncementCreateRequest(String title, String content, Long opinionPrice, Long commentPrice,
                                     LocalDate deadline) {
        this.title = title;
        this.content = content;
        this.opinionPrice = opinionPrice;
        this.commentPrice = commentPrice;
        this.deadline = deadline;
    }
}
