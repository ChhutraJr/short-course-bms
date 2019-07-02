package com.example.topic03pp.configurations.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {


        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());

        request.getSession().setAttribute("REDIRECT_URI", request.getRequestURI());
        request.getSession().setAttribute("REDIRECT_URL", request.getRequestURL().toString());

        System.out.println("Login Required");


        response.sendRedirect("/login");
    }
}
