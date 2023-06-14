package com.login;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ApiLoginSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiLoginSecurityApplication.class, args);
	}

	@Bean
	public static @NotNull WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NotNull CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
								"Access-Control-Allow-Methods", "Accept", "Authorization", "Content-Type", "Method",
								"Origin", "X-Forwarded-For", "X-Real-IP", "text/xml; charset=ISO-8859-1");
			}
		};
	}
}
