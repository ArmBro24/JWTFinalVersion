package com.example.demo.service.impl;


import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.SignUpRequest;
import com.example.demo.request.SigninRequest;
import com.example.demo.response.JwtAuthentResponse;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthentResponse signup(SignUpRequest request){
        User user = User.builder().id(request.getId())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        String jwt = jwtService.genToken(user);

        return JwtAuthentResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthentResponse signin(SigninRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid name or password", e);
        }


        UserDTO userDTO = userRepository.findByName(request.getName());
        String jwt = jwtService.getToken((UserDetails) userDTO);

        return JwtAuthentResponse.builder().token(jwt).build();
    }

}
