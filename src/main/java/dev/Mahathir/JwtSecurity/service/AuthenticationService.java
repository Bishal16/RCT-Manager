package dev.Mahathir.JwtSecurity.service;

import dev.Mahathir.JwtSecurity.controller.dto.AuthenticationRequest;
import dev.Mahathir.JwtSecurity.controller.dto.AuthenticationResponse;
import dev.Mahathir.JwtSecurity.controller.dto.RegisterRequest;
import dev.Mahathir.JwtSecurity.repo.UserInfoRepo;
import dev.Mahathir.JwtSecurity.entity.User;
import dev.Mahathir.JwtSecurity.config.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private UserInfoRepo userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate);
        //userData.setCreatedOn(date);.


        final var user = new User(null,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole(),
                date, // current date created on
                request.getPhoneNo(),
                request.getUserStatus()
        );
        userRepository.save(user);
        final var token = JwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        final var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        final var token = JwtService.generateToken(user);
        return new AuthenticationResponse(token);

    }
}
