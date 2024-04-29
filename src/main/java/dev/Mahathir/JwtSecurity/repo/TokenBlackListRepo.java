package dev.Mahathir.JwtSecurity.repo;

import dev.Mahathir.JwtSecurity.entity.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenBlackListRepo extends JpaRepository<TokenBlacklist, Integer> {
    Optional<Object> findByToken(String token);
}
