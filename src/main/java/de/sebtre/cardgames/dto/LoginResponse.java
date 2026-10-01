package de.sebtre.cardgames.dto;

import lombok.Getter;

@Getter
public class LoginResponse {

    private final Long id;
    private final String username;
    private final String token;

    public LoginResponse(Long id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }
}