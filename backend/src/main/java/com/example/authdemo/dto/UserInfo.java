package com.example.authdemo.dto;

import java.util.List;

public record UserInfo(Long id, String username, String email, List<String> roles) {}
