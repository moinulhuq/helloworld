package com.example.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//--------------- Extra --------------
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration

//--------------- Extra --------------

@SpringBootApplication
public class HelloworldApplication {

//--------------- Extra --------------
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
//--------------- Extra --------------

	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}

}
