package dev.Mahathir.JwtSecurity.service;

import dev.Mahathir.JwtSecurity.entity.TokenBlacklist;
import dev.Mahathir.JwtSecurity.repo.TokenBlackListRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenBlackListService {
//    private static Set<String> blacklist = new HashSet<>();
    private final TokenBlackListRepo tokenBlackListRepo;


    public void addToBlacklist(String token) {
//        blacklist.add(token);
        TokenBlacklist tokenBlacklist = new TokenBlacklist();
        tokenBlacklist.setToken(token);
        tokenBlackListRepo.save(tokenBlacklist);
    }


}