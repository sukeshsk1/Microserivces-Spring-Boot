package com.zosh.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zosh.model.User;
import com.zosh.repository.UserRepository;
import com.zosh.service.AuthService;
import com.zosh.service.KeycloakUserService;
import com.zoshpayload.request.SignupDto;
import com.zoshpayload.response.AuthResponse;
import com.zoshpayload.response.TokenResponse;

@Service

public class AuthServiceImpl implements AuthService {
@Autowired
    private  UserRepository userRepository;
    private  KeycloakUserService keycloakUserService;

    @Override
    public AuthResponse signup(SignupDto req) throws Exception {

        keycloakUserService.createUser(req);

        User createdUser = new User();
        createdUser.setEmail(req.getEmail());
        createdUser.setCreatedAt(LocalDateTime.now());
        createdUser.setPhone(req.getPhone());
        createdUser.setRole(req.getRole());
        createdUser.setFullName(req.getFullName());
        createdUser.setUsername(req.getUsername());
        userRepository.save(createdUser);


        TokenResponse tokenResponse= keycloakUserService.getAdminAccessToken(
                req.getUsername(),
                req.getPassword(),
                "password",
                null
        );

        AuthResponse response = new AuthResponse();
        response.setTitle("Welcome " + createdUser.getEmail());
        response.setMessage("Register success");
        response.setJwt(tokenResponse.getAccessToken());
        response.setRefresh_token(tokenResponse.getRefreshToken());
        return response;
    }

    @Override
    public AuthResponse getAccessTokenFromRefreshToken(String refreshToken) throws Exception {
        TokenResponse tokenResponse= keycloakUserService.getAdminAccessToken(
                null,
                null,
                "refresh_token",
                refreshToken
        );
        AuthResponse response = new AuthResponse();

        response.setMessage("Access token received");
        response.setJwt(tokenResponse.getAccessToken());
        response.setRefresh_token(tokenResponse.getRefreshToken());
        return response;
    }

    @Override
    public AuthResponse login(String username, String password) throws Exception {
        TokenResponse tokenResponse=keycloakUserService.getAdminAccessToken(
                username,
                password,
                "password",
                null
        );
        AuthResponse response = new AuthResponse();
        response.setTitle("Welcome Back " + username);
        response.setMessage("login success");
        response.setJwt(tokenResponse.getAccessToken());
        response.setRefresh_token(tokenResponse.getRefreshToken());
        return response;
    }


}
