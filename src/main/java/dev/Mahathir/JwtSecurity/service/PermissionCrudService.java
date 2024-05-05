package dev.Mahathir.JwtSecurity.service;

import dev.Mahathir.JwtSecurity.entity.Permission;
import dev.Mahathir.JwtSecurity.repo.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PermissionCrudService {

    private final PermissionRepository permissionRepo;
    public ResponseEntity<String> createPermission(Permission permission) {
        try{
            permissionRepo.save(permission);
            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deletePermission(Integer id) {
        try{
            permissionRepo.deleteById(id);
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Permission>> getPermissions() {
        try {
            return new ResponseEntity<>(permissionRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
