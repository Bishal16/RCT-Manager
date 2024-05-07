package dev.Mahathir.JwtSecurity.controller.dto;

import dev.Mahathir.JwtSecurity.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{
    private String token;
    private Set<Role> roles;
    LocalDateTime sessionStartDateTime;
}
