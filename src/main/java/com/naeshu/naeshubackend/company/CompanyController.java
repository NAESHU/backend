package com.naeshu.naeshubackend.company;

import com.naeshu.naeshubackend.auth.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/sign-up")
    public void signup(@RequestBody CompanySignUpRequest request) {
        companyService.signup(
                request.getCompanyName(),
                request.getCompanyId(),
                request.getCompanyPassword()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<CompanyLoginResponse> login(@RequestBody CompanyLoginRequest request) {
        Long companyId = companyService.login(
                request.companyId(),
                request.companyPassword()
        );
        String accessToken = jwtService.createToken(companyId, "COMPANY");
        return ResponseEntity.ok(new CompanyLoginResponse(accessToken));
    }
}
