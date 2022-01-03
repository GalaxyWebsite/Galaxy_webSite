package net.javaguides.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GalaxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalaxyApplication.class, args);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();		
		System.out.println(passwordEncoder.encode("caio"));//BCryptPasswordEncoder
	}

}
