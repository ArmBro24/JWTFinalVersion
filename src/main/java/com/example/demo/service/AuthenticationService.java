package com.example.demo.service;

import com.example.demo.request.SignUpRequest;
import com.example.demo.request.SigninRequest;
import com.example.demo.response.JwtAuthentResponse;


public interface AuthenticationService {
    JwtAuthentResponse signup(SignUpRequest request);

    JwtAuthentResponse signin(SigninRequest request);
}
