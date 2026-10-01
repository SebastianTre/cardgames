package de.sebtre.cardgames.controller;

import de.sebtre.cardgames.dto.UserResponse;
import de.sebtre.cardgames.model.AppUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public UserResponse getCurrentUser(
            @AuthenticationPrincipal AppUser user
    ) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}

