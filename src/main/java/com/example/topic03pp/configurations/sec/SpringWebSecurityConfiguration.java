//package com.example.topic03pp.configurations;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@Order(2)
//@EnableWebSecurity
//public class SpringWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private AuthenticationSuccessHandler successHandler;
//
//    @Autowired
//    private AuthenticationEntryPoint authenticationEntryPoint;
//
//
//    @Autowired
//    @Qualifier("userDetailsServiceImpl")
//    private UserDetailsService userDetailsService;
//
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    /*@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin_admin")
//                .password("{noop}admin").roles("ADMIN", "DBA", "USER")
//                .and()
//                .withUser("dba").password("{noop}dba").roles("DBA", "USER");
//
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}user").roles("USER");
//    }*/
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers("/resources/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
////        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN");
////        http.authorizeRequests().antMatchers("/dba/**").hasAnyRole("DBA");
////        http.authorizeRequests().antMatchers("/user/**").hasAnyRole("USER");
////        http.authorizeRequests().antMatchers("/swagger").authenticated();
//
////        http.csrf().disable();
//
////        http.formLogin()
////                .loginPage("/login")
////                //.loginProcessingUrl("/login") // default
////                .successHandler(successHandler)
////                .usernameParameter("username")
////                .passwordParameter("password");
//
//
//        // Add logout URL and when success return to ("/")
//        http.logout()
//                .logoutSuccessUrl("/")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//
//
//        http.exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedPage("/accessdenied");
//
//        http.sessionManagement().invalidSessionUrl("/swagger-v2");
//    }
//}
