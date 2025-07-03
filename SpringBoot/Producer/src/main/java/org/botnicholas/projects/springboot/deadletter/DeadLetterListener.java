package org.botnicholas.projects.springboot.deadletter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DeadLetterListener {
    @RabbitListener(queues = "dead-letter-queue")
    public void receiveDeadLetter(String message) {
        System.out.println("Dead Letter Received: " + message);
    }
}
