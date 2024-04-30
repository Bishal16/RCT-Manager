package dev.Mahathir.JwtSecurity.controller;

import dev.Mahathir.JwtSecurity.service.RoleCrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class RoleCrudController {
    private final RoleCrudService roleCrudService;
    @PostMapping("/createRole")
    ResponseEntity<String> createRole(@RequestParam String name){
        return roleCrudService.createRole(name);
    }
}
