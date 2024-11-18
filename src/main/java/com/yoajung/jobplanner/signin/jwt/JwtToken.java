package com.yoajung.jobplanner.signin.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtToken {
    private String accessToken;
    private String refreshToken;
}
