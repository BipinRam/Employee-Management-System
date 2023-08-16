package com.example.demo.token;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
