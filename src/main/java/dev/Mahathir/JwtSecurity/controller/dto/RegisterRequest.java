package dev.Mahathir.JwtSecurity.controller.dto;

import dev.Mahathir.JwtSecurity.user.Role;

import java.sql.Date;

public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        Date createdOn,
        String phoneNo,
        UserStatus userStatus,
        Role role

        ) {
}
