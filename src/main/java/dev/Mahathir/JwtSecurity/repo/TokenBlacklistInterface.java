package dev.Mahathir.JwtSecurity.repo;

public interface TokenBlacklistInterface {
    void addToBlacklist(String token);
    boolean isBlacklisted(String token);
}
