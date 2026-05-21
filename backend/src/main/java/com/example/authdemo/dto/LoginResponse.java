package com.example.authdemo.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        UserInfo user
) {}
