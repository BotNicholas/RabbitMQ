package org.botnicholas.projects.consumer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumerApplication {
	static final String queueName = "testQueue";

	@Bean
	public Queue testQueue() {
		return new Queue(queueName, true);
	}

	@RabbitListener(queues = queueName)
	public void listen(String message) {
		System.out.format("Received message from '%s' : %s;\n", queueName, message);
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
