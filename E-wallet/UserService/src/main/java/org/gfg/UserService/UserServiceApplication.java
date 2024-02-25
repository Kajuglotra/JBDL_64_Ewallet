package org.gfg.UserService;

import org.gfg.UserService.model.User;
import org.gfg.UserService.model.UserType;
import org.gfg.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = User.builder().
				phoneNo("txn-service").
				password(passwordEncoder.encode("txn-service")).
				authority("SERVICE").userType(UserType.SERVICE).
				build();
		userRepository.save(user);

	}
}
