//package com.example.topic03pp.configurations;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//@Configuration
//@Order(1)
//@EnableWebSecurity
//public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private AuthenticationEntryPoint authenticationEntryPoint;
//
//    private PasswordEncoder passwordEncoder;
//
//    public ApiSecurityConfiguration(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable();
//
//        http.antMatcher("/api/**")
//                .authorizeRequests()
//                .anyRequest()
//                .hasAnyRole("API");
//
//        http.httpBasic();
//
//        http.exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedPage("/accessdenied");
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("API_USERNAME").password(passwordEncoder.encode("API_PASSWORD"))
//                .roles("API");
//    }
//
////    public static void main(String[] args) {
////        String username = "API_USERNAME";
////        String password = "API_PASSWORD";
////
////        String encodedUsername = Base64.getUrlEncoder().encodeToString((username + ":" + password).getBytes());
////
////        System.out.println(encodedUsername);
////    }
//
//}
