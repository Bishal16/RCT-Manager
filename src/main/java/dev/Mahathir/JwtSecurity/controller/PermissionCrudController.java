package dev.Mahathir.JwtSecurity.controller;

import dev.Mahathir.JwtSecurity.entity.Permission;
import dev.Mahathir.JwtSecurity.service.PermissionCrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class PermissionCrudController {
    private final PermissionCrudService permissionService;
    @PostMapping("/createPermission")
    public ResponseEntity<String> createPermission(@RequestBody Permission permission){
        return permissionService.createPermission(permission);
    }

    @DeleteMapping("/deletePermission/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Integer id){
        return permissionService.deletePermission(id);
    }

    @GetMapping("/getPermissions")
    public ResponseEntity<List<Permission>> getPermissions(){
        return permissionService.getPermissions();
    }
}
