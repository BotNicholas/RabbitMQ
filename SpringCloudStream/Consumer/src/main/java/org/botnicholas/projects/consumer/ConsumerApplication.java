package org.botnicholas.projects.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.function.Consumer;

@SpringBootApplication
public class ConsumerApplication {
    @Bean
    public Consumer<String> myInput() {
        return message -> {
            System.out.println("Received new message: " + message);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
