package dev.Mahathir.JwtSecurity.controller;

import dev.Mahathir.JwtSecurity.controller.dto.AuthenticationRequest;
import dev.Mahathir.JwtSecurity.controller.dto.AuthenticationResponse;
import dev.Mahathir.JwtSecurity.controller.dto.RegisterRequest;
import dev.Mahathir.JwtSecurity.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public record AuthController(AuthenticationService authenticationService) {


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
