package com.example.topic03pp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {


    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:s");
        try
        {

            date = simpleDateFormat.parse("Tue Dec 31 00:00:00 ICT 2019");
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
        }


        System.out.println(date);


        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("API_PASSWORD"));

        System.out.println(passwordEncoder.matches("API_PASSWORD", "$2a$10$x.inJWuGwP.gs7sIfR6nzezkKWrN1nCuhCezmnLldq5omSHKzT65i"));


    }
}
