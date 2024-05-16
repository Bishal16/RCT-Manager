package dev.Mahathir.JwtSecurity.controller.test;

import dev.Mahathir.JwtSecurity.entity.Role;
import dev.Mahathir.JwtSecurity.repo.RoleRepository;
import dev.Mahathir.JwtSecurity.service.test.CrudServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController2 extends CrudServiceImpl<Role, Integer>{


    public RoleController2(RoleRepository roleRepository) {
        super(roleRepository);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/getRoles2")
    public Iterable<Role> getRoles() {
        return getAll();
    }

    @PostMapping("/createRole2")
    public Role createRole(@RequestBody Role role){ return create(role);}

    @PostMapping("/deleteRole2/{id}")
    public void deleteRole(@PathVariable Integer id) { delete(id); }

    @PostMapping("/editRole2/{id}")
    public Role editRole(@RequestBody Role role, @PathVariable Integer id){ return update(role, id); }


}
