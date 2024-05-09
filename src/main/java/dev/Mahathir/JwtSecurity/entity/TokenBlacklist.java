package dev.Mahathir.JwtSecurity.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TokenBlacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column(length = 1000)
    private String token;
}
