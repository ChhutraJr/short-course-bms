package com.example.topic03pp.configurations.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class AccessDeniedHandlerCustomization implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {

        System.out.println("You don't have permission!!!");


        System.out.println(httpServletRequest.getSession().getMaxInactiveInterval()); // 1800 seconds (30 mn)
        System.out.println(httpServletRequest.getAuthType());
        System.out.println(httpServletRequest.getLocale().getCountry());
        System.out.println(httpServletRequest.getSession().getServletContext().getServerInfo());


//        httpServletRequest.getSession().setAttribute("msg", "You don't have permission!!!");

        httpServletResponse.encodeRedirectURL("/swagger-v2");

        httpServletResponse.sendError(403, "You have no permission to this URL!!!");

//        httpServletResponse.sendRedirect("/meme");


    }
}
