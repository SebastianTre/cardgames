package de.sebtre.cardgames.service;

import de.sebtre.cardgames.dto.LoginRequest;
import de.sebtre.cardgames.dto.LoginResponse;
import de.sebtre.cardgames.dto.RegistrationRequest;
import de.sebtre.cardgames.dto.UserResponse;
import de.sebtre.cardgames.exception.EmailAlreadyExistsException;
import de.sebtre.cardgames.exception.InvalidCredentialsException;
import de.sebtre.cardgames.exception.UsernameAlreadyExistsException;
import de.sebtre.cardgames.model.AppUser;
import de.sebtre.cardgames.repository.AppUserRepository;
import de.sebtre.cardgames.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import de.sebtre.cardgames.security.JwtService;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AppUserService(AppUserRepository appUserRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

//    public AppUser createTestUser() {
//
//        AppUser user = new AppUser();
//
//        user.setUsername("TestPlayer");
//        user.setEmail("test@cardgames.de");
//        user.setPassword("test123");
//
//        return appUserRepository.save(user);
//    }

    public UserResponse registerUser(RegistrationRequest request) {

        if (appUserRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username bereits vergeben");
        }

        if (appUserRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("E-Mail bereits registriert");
        }

        AppUser user = new AppUser();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        AppUser savedUser = appUserRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt()
        );
    }

    public LoginResponse login(LoginRequest request) {

        AppUser user = appUserRepository
                .findByUsernameOrEmail(request.getLogin(), request.getLogin())
                .orElseThrow(() -> new InvalidCredentialsException("Ungültige Anmeldedaten"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Ungültige Anmeldedaten");
        }

        String token = jwtService.generateToken(user.getUsername());

        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                token
        );
    }
}