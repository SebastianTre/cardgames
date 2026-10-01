package de.sebtre.cardgames;

import de.sebtre.cardgames.dto.RegistrationRequest;
import de.sebtre.cardgames.model.AppUser;
import de.sebtre.cardgames.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CardgamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardgamesApplication.class, args);
	}

//	@Bean
//	CommandLineRunner testUser(AppUserService appUserService) {
//		return args -> {
//			appUserService.createTestUser();
//			System.out.println("Test User wurde gespeichert");
//		};
//	}

//	@Bean
//	CommandLineRunner testRegistration(AppUserService appUserService) {
//		return args -> {
//
//			RegistrationRequest request = new RegistrationRequest();
//
//			request.setUsername("Spieler1");
//			request.setEmail("spieler1@test.de");
//			request.setPassword("passwort123");
//
//			AppUser user = appUserService.registerUser(request);
//
//			System.out.println("Registriert: " + user.getUsername());
//		};
//	}

}
