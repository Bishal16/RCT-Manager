package dev.Mahathir.JwtSecurity.controller;

import dev.Mahathir.JwtSecurity.entity.Role;
import dev.Mahathir.JwtSecurity.service.RoleCrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class RoleCrudController {
    private final RoleCrudService roleCrudService;
    @PostMapping("/createRole")
    public ResponseEntity<String> createRole(@RequestParam String name){
        return roleCrudService.createRole(name);
    }

    @GetMapping("/getRoles")
    public ResponseEntity<List<Role>> getRoles(){
        return roleCrudService.getRoles();
    }
}
