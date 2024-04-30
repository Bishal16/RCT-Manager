package dev.Mahathir.JwtSecurity.controller;

import dev.Mahathir.JwtSecurity.controller.dto.AuthenticationRequest;
import dev.Mahathir.JwtSecurity.controller.dto.AuthenticationResponse;
import dev.Mahathir.JwtSecurity.controller.dto.RegisterRequest;
import dev.Mahathir.JwtSecurity.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
