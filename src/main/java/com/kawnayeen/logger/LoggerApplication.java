package com.kawnayeen.logger;

import com.kawnayeen.logger.model.RoleConstant;
import com.kawnayeen.logger.model.entity.Account;
import com.kawnayeen.logger.model.entity.Application;
import com.kawnayeen.logger.model.entity.Role;
import com.kawnayeen.logger.repository.AccountRepository;
import com.kawnayeen.logger.repository.ApplicationRepository;
import com.kawnayeen.logger.repository.RoleRepository;
import com.kawnayeen.logger.security.token.auth.JwtAuthenticationFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
public class LoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggerApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter tokenAuthFilter() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Bean
    GuavaCacheManager cacheManager() {
        return new GuavaCacheManager();
    }

    @Bean
    CommandLineRunner initRolesAndAccount(RoleRepository roleRepository, AccountRepository accountRepository,
                                          ApplicationRepository applicationRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role user = new Role();
            user.setCode(RoleConstant.PREFIX + RoleConstant.USER);
            user.setLabel(RoleConstant.USER);
            user = roleRepository.save(user);

            Role admin = new Role();
            admin.setCode(RoleConstant.PREFIX + RoleConstant.ADMIN);
            admin.setLabel(RoleConstant.ADMIN);
            admin = roleRepository.save(admin);

            Account firstAccount = new Account();
            firstAccount.setUsername("Anan");
            firstAccount.setPassword(passwordEncoder.encode("anan"));
            firstAccount.getRoles().add(user);
            accountRepository.save(firstAccount);

            Account secondAccount = new Account();
            secondAccount.setUsername("Kamarul");
            secondAccount.setPassword(passwordEncoder.encode("kamarul"));
            secondAccount.getRoles().add(user);
            secondAccount.getRoles().add(admin);
            accountRepository.save(secondAccount);

            Application application = new Application();
            application.setDisplayName("Test Application");
            application.setApplicationId("test_app_id");
            application.setApplicationSecret("test_app_secret");
            application.setAccount(firstAccount);
            applicationRepository.save(application);
        };
    }
}
