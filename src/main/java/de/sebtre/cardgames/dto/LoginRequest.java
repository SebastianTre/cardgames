package de.sebtre.cardgames.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Username oder E-Mail darf nicht leer sein")
    private String login;

    @NotBlank(message = "Passwort darf nicht leer sein")
    private String password;
}