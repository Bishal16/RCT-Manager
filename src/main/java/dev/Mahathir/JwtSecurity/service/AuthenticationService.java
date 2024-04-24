package dev.Mahathir.JwtSecurity.service;

import dev.Mahathir.JwtSecurity.controller.dto.AuthenticationRequest;
import dev.Mahathir.JwtSecurity.controller.dto.AuthenticationResponse;
import dev.Mahathir.JwtSecurity.controller.dto.RegisterRequest;
import dev.Mahathir.JwtSecurity.repo.UserInfoRepo;
import dev.Mahathir.JwtSecurity.user.User;
import dev.Mahathir.JwtSecurity.config.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public record AuthenticationService(UserInfoRepo userRepository,
                                    PasswordEncoder passwordEncoder,
                                    AuthenticationManager authenticationManager) {
    public AuthenticationResponse register(RegisterRequest request) {
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate);
        //userData.setCreatedOn(date);


        final var user = new User(null,
                request.firstName(),
                request.lastName(),
                request.email(),
                passwordEncoder.encode(request.password()),
                request.role(),
                date, // current date created on
                request.phoneNo(),
                request.userStatus()
        );
        userRepository.save(user);
        final var token = JwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        final var user = userRepository.findByEmail(request.email()).orElseThrow();
        final var token = JwtService.generateToken(user);
        return new AuthenticationResponse(token);

    }
}
