package com.naeshu.naeshubackend.announcement;

import com.naeshu.naeshubackend.auth.Auth;
import com.naeshu.naeshubackend.auth.jwt.JwtService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/naeshu/announce")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void create(
            @Auth Long companyId,
            @RequestBody AnnouncementCreateRequest request) {
        announcementService.create(
                companyId,
                request.getTitle(),
                request.getContent(),
                request.getOpinionPrice(),
                request.getCommentPrice(),
                request.getDeadline()
        );
    }

}
