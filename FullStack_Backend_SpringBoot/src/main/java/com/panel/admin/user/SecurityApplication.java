package com.panel.admin.lup;

import com.panel.admin.lup.auth.AuthenticationService;
import com.panel.admin.lup.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.panel.admin.lup.user.Role.ADMIN;
import static com.panel.admin.lup.user.Role.USER;


@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.RegisteredBy("admin@paytm.com")
					.firstname("ADMIN")
					.lastname("Singh")
					.email("admin@paytm.com")
					.password("J@tin123")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAuthenticationResponse().getAccessToken());

			var manager = RegisterRequest.builder()
					.RegisteredBy("user@paytm.com")
					.firstname("USER")
					.lastname("Singh")
					.email("user@paytm.com")
					.password("J@tin123")
					.role(USER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAuthenticationResponse().getAccessToken());

		};
	}
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				WebMvcConfigurer.super.addCorsMappings(registry);
				registry.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("http://localhost:3000")
						.allowedHeaders("*")
						.allowCredentials(false)
						.maxAge(-1);
			}
		};
	}
}
