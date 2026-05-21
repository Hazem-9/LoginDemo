package com.example.authdemo.dto;

import java.time.Instant;

public record ApiError(String code, String message, Instant timestamp) {}
