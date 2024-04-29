package dev.Mahathir.JwtSecurity.service;

public interface TokenBlacklistChecker {
    public boolean isBlacklisted(String token);
}
