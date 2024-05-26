package com.naeshu.naeshubackend.opinion.controller;

import com.naeshu.naeshubackend.auth.Auth;
import com.naeshu.naeshubackend.auth.AuthInfo;
import com.naeshu.naeshubackend.common.UnAuthorizedException;
import com.naeshu.naeshubackend.opinion.dto.request.OpinionRequest;
import com.naeshu.naeshubackend.opinion.dto.response.OpinionResponse;
import com.naeshu.naeshubackend.opinion.service.OpinionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/naeshu/opinion")
@Slf4j
public class OpinionController {

    private final OpinionService opinionService;

    @PostMapping("/{announceNumber}/register")
    public ResponseEntity<Void> create(
            @PathVariable("announceNumber") Long announceId,
            @Auth AuthInfo authInfo,
            @RequestBody OpinionRequest opinionRequest)
    {
        verifyRole(authInfo);
        opinionService.create(authInfo.memberId(), announceId, opinionRequest.content());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{announceNumber}")
    public ResponseEntity<List<OpinionResponse>> findAllByAnnouncement(
            @PathVariable("announceNumber") Long announceId,
            @Auth AuthInfo authInfo
    ) {
        List<OpinionResponse> optionResponseList = opinionService.findAllByAnnouncement(announceId);
        return ResponseEntity.ok(optionResponseList);
    }

    @PutMapping("/{announceNumber}/{opinionId}")
    public ResponseEntity<Void> selectOpinion(
            @PathVariable("announceNumber") Long announceId,
            @PathVariable("opinionId") Long opinionId,
            @Auth AuthInfo authInfo
    ) {
        verifyCompanyRole(authInfo);
        opinionService.selectOpinion(authInfo.memberId(), announceId, opinionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyRole(AuthInfo authInfo) {
        if (!authInfo.role().equals("USER")) {
            throw new UnAuthorizedException("USER 권한이 아닙니다.");
        }
    }

    private void verifyCompanyRole(AuthInfo authInfo) {
        if (!authInfo.role().equals("COMPANY")) {
            throw new UnAuthorizedException("COMPANY 권한이 아닙니다.");
        }
    }
}
