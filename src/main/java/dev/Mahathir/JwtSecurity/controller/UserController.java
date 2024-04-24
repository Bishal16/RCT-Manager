package dev.Mahathir.JwtSecurity.controller;

import dev.Mahathir.JwtSecurity.service.ResponseService;
import dev.Mahathir.JwtSecurity.repo.UserInfoRepo;
import dev.Mahathir.JwtSecurity.service.TokenBlackListService;
import dev.Mahathir.JwtSecurity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/rest/auth/")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class UserController {
    private  final ResponseService service;
    private  final UserInfoRepo userInfoRepoService;
    private  final TokenBlackListService inMemoryTokenBlacklist;
//    private final RoleRepository roleInfoRepo;


    @GetMapping("/greet")
    String greetUser(){
        return "greet";
    }

//    @PostMapping("/addUser")
//    ResponseEntity<String> createUser(@RequestBody User userData){
//        return service.createNewUser(userData);
//    }

    @DeleteMapping("removeUser/{id}")
    ResponseEntity<?> deleteUserById(@PathVariable("id") Integer id){
        try{
            userInfoRepoService.deleteById(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("editUser/{id}")
    ResponseEntity<?> editUser(@PathVariable("id") Integer id, @RequestBody User user){
        try{
            service.editUser(id, user);
            return new ResponseEntity<>("User edited successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to edit user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allUser")
    List<User> getAllUser (){
        return userInfoRepoService.findAll();
    }
    @GetMapping("/user/{id}")
    Optional<User> getUser (@PathVariable Integer id){
        return userInfoRepoService.findById(id);
    }
    @PostMapping("/logOut")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = service.extractTokenFromRequest(request);
        inMemoryTokenBlacklist.addToBlacklist(token);
        return ResponseEntity.ok("Logged out/blacklisted successfully");
    }
}
