package com.example.demo.controller;

import com.example.demo.request.SignUpRequest;
import com.example.demo.request.SigninRequest;
import com.example.demo.response.JwtAuthentResponse;
import com.example.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authentService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthentResponse> signup(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authentService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthentResponse> signin(@RequestBody SigninRequest request){
        return ResponseEntity.ok(authentService.signin(request));
    }
}
