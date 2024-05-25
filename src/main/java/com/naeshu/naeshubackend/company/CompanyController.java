package com.naeshu.naeshubackend.company;

import com.naeshu.naeshubackend.auth.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/naeshu/company")
public class CompanyController {

    private final CompanyService companyService;
    private final JwtService jwtService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void signup(@RequestBody CompanySignUpRequest request) {
        companyService.signup(
                request.getCompanyName(),
                request.getCompanyId(),
                request.getCompanyPassword()
        );
    }

}
