package dev.Mahathir.JwtSecurity.controller;

import dev.Mahathir.JwtSecurity.controller.dto.PermissionListRequest;
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

    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Integer id) { return roleCrudService.deleteRole(id); }

    @PostMapping("/setRolePermissions/{roleId}")
    public ResponseEntity<String> setRolePermissions(@PathVariable Integer roleId, @RequestBody PermissionListRequest permissionListRequest){
        return roleCrudService.setRolePermissions(roleId, permissionListRequest.getPermissionIdList());
    }

}
