package com.zosh.service;

import com.zoshpayload.request.SignupDto;
import com.zoshpayload.response.AuthResponse;

public interface AuthService {
	AuthResponse login(String username, String password) throws Exception;
    AuthResponse signup(SignupDto req) throws Exception;
    AuthResponse getAccessTokenFromRefreshToken(String refreshToken) throws Exception;

}
