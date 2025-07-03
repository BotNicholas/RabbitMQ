package org.botnicholas.projects.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class SendingController {
    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName = "testExchange";
//    private final String exchangeName = "demo-queue";

    @PostMapping("/messages")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rabbitTemplate.convertAndSend(exchangeName, "test.key", message); //automatically converts payload to Message and sends it.
//        rabbitTemplate.convertAndSend("demo-exchange", "demo-queue", message);
        return ResponseEntity.ok(String.format("Message '%s' is sent!", message));
    }

    @GetMapping("/emit-to-new-key")
    public ResponseEntity<String> emitToNewKey() {
        rabbitTemplate.convertAndSend(exchangeName, "new.key", "Some bit info"); // here we're reusing the exchange
//        rabbitTemplate.convertAndSend("newExchange", "new.key", "Some bit info"); //you can find this exchange config inNewTopicConsumer
        return ResponseEntity.ok("Message is sent!");
    }
}
