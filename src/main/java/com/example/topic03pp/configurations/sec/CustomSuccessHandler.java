package com.example.topic03pp.configurations.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@Component
@Configuration
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {


        HttpSession httpSession = request.getSession();
        httpSession.setMaxInactiveInterval(50000);

        System.out.println("ok success login");

        String redirectUrl = (String) request.getSession().getAttribute("REDIRECT_URL");

        System.out.println(redirectUrl);


        redirectUrl = "/swagger-v2";


        /*if (redirectUrl == null){
            for (GrantedAuthority grantedAuthority:
                authentication.getAuthorities()) {
//            System.out.println(authentication.getName());
//            System.out.println(grantedAuthority.getAuthority());
                if (grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
                    redirectUrl = "/admin";
                else if (grantedAuthority.getAuthority().equals("ROLE_DBA")) {
                    redirectUrl = "/dba";
                }
                else if (grantedAuthority.getAuthority().equals("ROLE_USER"))
                    redirectUrl = "/user";
            }
        }*/
        response.sendRedirect(redirectUrl);

    }
}
