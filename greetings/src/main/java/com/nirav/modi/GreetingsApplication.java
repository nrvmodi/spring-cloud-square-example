package com.nirav.modi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
public class GreetingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreetingsApplication.class, args);
    }


    private int port;

    @EventListener
    public void ready(WebServerInitializedEvent event) {
        port = event.getWebServer().getPort();
    }

    @GetMapping("/hello/{name}")
    public String getGreet(@PathVariable String name) {
        System.out.println("Greeings api called");
        return "Hello, localhost: " + port + " : " + name;
    }

}
