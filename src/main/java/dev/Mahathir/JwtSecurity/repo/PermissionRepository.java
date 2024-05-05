package dev.Mahathir.JwtSecurity.repo;

import dev.Mahathir.JwtSecurity.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
