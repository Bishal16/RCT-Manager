package dev.Mahathir.JwtSecurity.service;


import dev.Mahathir.JwtSecurity.repo.UserInfoRepo;
import dev.Mahathir.JwtSecurity.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserCrudService {

    private final UserInfoRepo userRepo;


    @Autowired
    public UserCrudService(UserInfoRepo userRepo){
        this.userRepo = userRepo;
    }



    public ResponseEntity<String> editUser(Integer id, User user){
        try{
            User existingUser = userRepo.findById(id).orElseThrow(() ->new RuntimeException("User not found"));

            existingUser.setEmail(user.getEmail());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPhoneNo(user.getPhoneNo());
            existingUser.setUserStatus(user.getUserStatus());
            existingUser.setPassword(user.getPassword());

            userRepo.save(existingUser);

            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); //---------------------------------------------------???
        }

    }



    public String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    public ResponseEntity<String> deleteUserById(Integer id) {
        try{
            userRepo.deleteById(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getUser(Integer id) {
        try {
            Optional<User> optionalUser = userRepo.findById(id);
            if(optionalUser.isEmpty()) throw new Exception("Not Found");
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
