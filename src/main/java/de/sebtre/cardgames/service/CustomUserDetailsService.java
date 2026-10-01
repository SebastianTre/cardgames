package de.sebtre.cardgames.service;

import de.sebtre.cardgames.model.AppUser;
import de.sebtre.cardgames.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public CustomUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return appUserRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Benutzer nicht gefunden"
                        )
                );
    }
}
