package com.empresa.teste;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableAutoConfiguration
public class RestSample  {

    @RequestMapping("/hello")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RestSample.class, args);
    }

}