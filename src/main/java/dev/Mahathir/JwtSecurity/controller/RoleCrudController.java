package dev.Mahathir.JwtSecurity.controller;

import dev.Mahathir.JwtSecurity.dto.PermissionListRequest;
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
    public ResponseEntity<String> createRole(@RequestBody Role role){
        return roleCrudService.createRole(role);
    }

    @PostMapping("/getRoles")
    public ResponseEntity<List<Role>> getRoles(){
        return roleCrudService.getRoles();
    }

    @PostMapping("/getRole/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id){
        return roleCrudService.getRole(id);
    }

    @PostMapping("/deleteRole/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Integer id) { return roleCrudService.deleteRole(id); }

    @PostMapping("/setRolePermissions/{roleId}")
    public ResponseEntity<String> setRolePermissions(@PathVariable Integer roleId, @RequestBody PermissionListRequest permissionListRequest){
        return roleCrudService.setRolePermissions(roleId, permissionListRequest.getPermissionIdList());
    }


}
