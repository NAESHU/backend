package com.naeshu.naeshubackend.auth.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperty(
        String secretKey,
        Long accessTokenExpirationDay
) {
}
