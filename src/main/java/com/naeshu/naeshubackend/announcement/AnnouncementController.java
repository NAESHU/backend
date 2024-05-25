package com.naeshu.naeshubackend.announcement;

import com.naeshu.naeshubackend.auth.Auth;
import com.naeshu.naeshubackend.auth.AuthInfo;
import com.naeshu.naeshubackend.auth.jwt.JwtService;
import com.naeshu.naeshubackend.common.UnAuthorizedException;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            @Auth AuthInfo authInfo,
            @RequestBody AnnouncementCreateRequest request) {
        verifyRole(authInfo);
        announcementService.create(
                authInfo.memberId(),
                request.getTitle(),
                request.getContent(),
                request.getOpinionPrice(),
                request.getCommentPrice(),
                request.getDeadline()
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnnouncementResponse>> findAll() {
        List<AnnouncementResponse> response = announcementService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{announceId}")
    public ResponseEntity<AnnouncementResponse> findById(
            @PathVariable("announceId") Long announceId
    ) {
        AnnouncementResponse response = announcementService.findById(announceId);
        return ResponseEntity.ok(response);
    }

    private void verifyRole(AuthInfo authInfo) {
        if (!authInfo.role().equals("COMPANY")) {
            throw new UnAuthorizedException("COMPANY 권한이 아닙니다.");
        }
    }
}
