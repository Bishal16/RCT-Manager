package dev.Mahathir.JwtSecurity.controller.dto;

import dev.Mahathir.JwtSecurity.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest{
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Date createdOn;
        private String phoneNo;
        private UserStatus userStatus;
        private List<Role> roles;

}
