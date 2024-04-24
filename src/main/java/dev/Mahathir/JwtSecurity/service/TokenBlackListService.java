package dev.Mahathir.JwtSecurity.service;

import dev.Mahathir.JwtSecurity.repo.TokenBlacklist;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenBlackListService {
    private static Set<String> blacklist = new HashSet<>();



    public static void addToBlacklist(String token) {
        blacklist.add(token);
    }


    public static boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }


}