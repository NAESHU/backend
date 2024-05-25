package com.naeshu.naeshubackend.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.naeshu.naeshubackend.common.UnAuthorizedException;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final long accessTokenExpirationDayToMills;
    private final Algorithm algorithm;

    public JwtService(JwtProperty jwtProperty) {
        this.accessTokenExpirationDayToMills = jwtProperty.accessTokenExpirationDay() * 24 * 60 * 60 * 1000;
        this.algorithm = Algorithm.HMAC512(jwtProperty.secretKey());
    }

    public String createToken(Long memberId) {
        return JWT.create()
                .withExpiresAt(new Date(
                        accessTokenExpirationDayToMills + System.currentTimeMillis()
                ))
                .withIssuedAt(new Date())
                .withClaim("memberId", memberId)
                .sign(algorithm);
    }

    public Long extractMemberId(String token) {
        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getClaim("memberId")
                    .asLong();
        } catch (JWTVerificationException e) {
            throw new UnAuthorizedException("유효하지 않은 토큰입니다.");
        }
    }
}