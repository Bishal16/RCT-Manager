package dev.Mahathir.JwtSecurity.service;

import dev.Mahathir.JwtSecurity.entity.Role;
import dev.Mahathir.JwtSecurity.repo.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleCrudService {
    private final RoleRepository roleRepository;
    public ResponseEntity<String> createRole(String name) {
        try{
            Role role = new Role();
            role.setName(name);
            roleRepository.save(role);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);

        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleRepository.findAll(), HttpStatus.OK);
    }
}
