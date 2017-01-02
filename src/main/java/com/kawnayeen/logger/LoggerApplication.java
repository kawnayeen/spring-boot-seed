package com.kawnayeen.logger;

import com.kawnayeen.logger.model.Account;
import com.kawnayeen.logger.model.Role;
import com.kawnayeen.logger.model.RoleConstant;
import com.kawnayeen.logger.repository.AccountRepository;
import com.kawnayeen.logger.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggerApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner initRolesAndAccount(RoleRepository roleRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder){
		return args ->{
			Role user = new Role();
			user.setCode(RoleConstant.PREFIX+RoleConstant.USER);
			user.setLabel(RoleConstant.USER);
			user = roleRepository.save(user);

			Role admin = new Role();
			admin.setCode(RoleConstant.PREFIX+RoleConstant.ADMIN);
			admin.setLabel(RoleConstant.ADMIN);
			admin = roleRepository.save(admin);

			Account firstAccount = new Account();
			firstAccount.setUsername("Anan");
			firstAccount.setPassword(passwordEncoder.encode("anan"));
			firstAccount.getRoles().add(user);
			accountRepository.save(firstAccount);
			System.out.println(firstAccount.toString());

			Account secondAccount = new Account();
			secondAccount.setUsername("Kamarul");
			secondAccount.setPassword(passwordEncoder.encode("kamarul"));
			secondAccount.getRoles().add(user);
			secondAccount.getRoles().add(admin);
			accountRepository.save(secondAccount);
			System.out.println(secondAccount.toString());
		};
	}
}
