package dev.Mahathir.JwtSecurity.service;

import dev.Mahathir.JwtSecurity.entity.Permission;
import dev.Mahathir.JwtSecurity.entity.Role;
import dev.Mahathir.JwtSecurity.repo.PermissionRepository;
import dev.Mahathir.JwtSecurity.repo.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleCrudService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    public ResponseEntity<String> createRole(Role roleOnRequest) {
        try{
            Role role = new Role();
            role.setName(roleOnRequest.getName());
            role.setDescription(roleOnRequest.getDescription());
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

    public ResponseEntity<String> deleteRole(Integer id) {
        try{
            roleRepository.deleteById(id);
            return new ResponseEntity<>("Role Deleted", HttpStatus.OK);
        }catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String> setRolePermissions(Integer roleId, List<Integer> permissionIdList) {
        try{
            Role validRole = roleRepository.findById(roleId).orElseThrow(() -> new Exception("Invalid Role Id"));

            List<Permission> permissions = validRole.getPermissions();

            for(Integer permissionId : permissionIdList){
                Permission validPermission = permissionRepository.findById(permissionId).orElseThrow(() -> new Exception(("No Permission with Id - " + permissionId)));
                permissions.add(validPermission);
            }

            validRole.setPermissions(permissions);
            roleRepository.save(validRole);

            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }
}
