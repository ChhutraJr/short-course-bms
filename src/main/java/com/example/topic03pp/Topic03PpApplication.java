package com.example.topic03pp;

import com.example.topic03pp.repositories.UserRepository;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class Topic03PpApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Topic03PpApplication.class, args);

        UserRepository userRepository = context.getBean(UserRepository.class);

        System.out.println(userRepository.loadUserByUsernameRepository("API_USERNAME"));

        String encryptedPassword = userRepository.loadUserByUsernameRepository("API_USERNAME").getPassword();

        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);

//        String mypassword = passwordEncoder.encode("ehan");

        System.out.println(passwordEncoder.matches("API_PASSWORD", encryptedPassword));

    }
}
