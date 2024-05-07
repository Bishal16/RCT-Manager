package dev.Mahathir.JwtSecurity.service;

import dev.Mahathir.JwtSecurity.controller.dto.AuthenticationRequest;
import dev.Mahathir.JwtSecurity.controller.dto.AuthenticationResponse;
import dev.Mahathir.JwtSecurity.controller.dto.RegisterRequest;
import dev.Mahathir.JwtSecurity.entity.Role;
import dev.Mahathir.JwtSecurity.repo.RoleRepository;
import dev.Mahathir.JwtSecurity.repo.UserInfoRepo;
import dev.Mahathir.JwtSecurity.entity.User;
import dev.Mahathir.JwtSecurity.config.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserInfoRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    public ResponseEntity<String> register(RegisterRequest request) {
        try{
            LocalDate localDate = LocalDate.now();
            Date date = Date.valueOf(localDate);

            Set<Role> validRoles = new HashSet<>();
            for (Role requestRole : request.getRoles()) {
                Optional<Role> validRole = roleRepository.findByName(requestRole.getName());
                if(validRole.isEmpty()) throw new Exception("Role does not exist in Database");
                validRoles.add(validRole.get());
            }

            final var user = new User(null,
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    passwordEncoder.encode(request.getPassword()),
                    date, // current date created on
                    request.getPhoneNo(),
                    request.getUserStatus(),
                    validRoles
            );
            userRepository.save(user);
           // final var token = JwtService.generateToken(user);
            return new ResponseEntity<>("REGISTERED", HttpStatus.CREATED);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        final var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        Set<Role> roles = user.getRoles();
        final var token = JwtService.generateToken(user);
        return new AuthenticationResponse(token, roles, LocalDateTime.now());
    }
}
