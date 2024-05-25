package com.naeshu.naeshubackend.opinion.controller;

import com.naeshu.naeshubackend.auth.Auth;
import com.naeshu.naeshubackend.auth.AuthInfo;
import com.naeshu.naeshubackend.common.UnAuthorizedException;
import com.naeshu.naeshubackend.opinion.dto.request.OpinionRequest;
import com.naeshu.naeshubackend.opinion.service.OpinionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
@RequestMapping("/naeshu/opinion")
@Slf4j
public class OpinionController {

    private final OpinionService opinionService;

    @PostMapping("/{announceNumber}/register")
    public ResponseEntity<Void> create(@PathVariable("announceNumber") Long announceId,
                                       @Auth AuthInfo authInfo,
                                       @RequestBody OpinionRequest opinionRequest){
        verifyRole(authInfo);
        log.info("{}", opinionRequest.content());
        opinionService.create(authInfo.memberId(), announceId, opinionRequest.content());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private void verifyRole(AuthInfo authInfo) {
        if (!authInfo.role().equals("USER")) {
            throw new UnAuthorizedException("USER 권한이 아닙니다.");
        }
    }
}
