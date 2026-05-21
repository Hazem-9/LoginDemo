package com.example.authdemo.service;

import com.example.authdemo.dto.LoginRequest;
import com.example.authdemo.dto.LoginResponse;
import com.example.authdemo.dto.UserInfo;
import com.example.authdemo.entity.AppUser;
import com.example.authdemo.repository.UserRepository;
import com.example.authdemo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        AppUser user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        UserInfo userInfo = new UserInfo(user.getId(), user.getUsername(), user.getEmail(), user.getRoles().stream().toList());
        return new LoginResponse(jwtService.generateAccessToken(user), jwtService.generateRefreshToken(user), "Bearer", userInfo);
    }
}
