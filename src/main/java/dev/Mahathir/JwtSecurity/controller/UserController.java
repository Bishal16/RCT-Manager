package dev.Mahathir.JwtSecurity.controller;

import dev.Mahathir.JwtSecurity.service.UserCrudService;
import dev.Mahathir.JwtSecurity.repo.UserInfoRepo;
import dev.Mahathir.JwtSecurity.service.TokenBlackListService;
import dev.Mahathir.JwtSecurity.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController


@AllArgsConstructor
public class UserController {
    private  final UserCrudService userCrudService;
    private  final TokenBlackListService tokenBlackListService;


    @PostMapping("/greet")
    String greetUser(){
        return "greetings";
    }


    @PostMapping("deleteUser/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Integer id){
        return userCrudService.deleteUserById(id);
    }

    @PostMapping("editUser/{id}")
    public ResponseEntity<String> editUser(@PathVariable("id") Integer id, @RequestBody User user){
        return userCrudService.editUser(id, user);
    }

    @PostMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUser (){
        return userCrudService.getAllUser();
    }
    @PostMapping("/getUser/{id}")
    public ResponseEntity<Object> getUser (@PathVariable Integer id){
        return userCrudService.getUser(id);
    }


    @PostMapping("/logOut")//----------------------------------------------------------------------------------could be handled somewhere else
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = userCrudService.extractTokenFromRequest(request);
        tokenBlackListService.addToBlacklist(token);
        return ResponseEntity.ok("Logged out/blacklisted successfully");
    }
}
