package dev.Mahathir.JwtSecurity.repo;

import dev.Mahathir.JwtSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
