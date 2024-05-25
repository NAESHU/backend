package com.naeshu.naeshubackend.announcement;

import com.naeshu.naeshubackend.common.BidStatus;
import java.time.LocalDate;

public record AnnouncementResponse(
    Long id, String title, String content, Long opinionPrice, Long commentPrice,
    LocalDate createdAt, LocalDate deadline, BidStatus bidStatus, Long companyId
) {
}
