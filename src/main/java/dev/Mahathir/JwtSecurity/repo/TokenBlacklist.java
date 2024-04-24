package dev.Mahathir.JwtSecurity.repo;

public interface TokenBlacklist {
    void addToBlacklist(String token);
    boolean isBlacklisted(String token);
}
