package org.botnicholas.projects.newtopicconsumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NewTopicConsumerApplication {
    static final String queueName = "new";
//    static final String exchangeName = "newExchange";
    static final String exchangeName = "testExchange";

    @Bean
    public Queue testQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public Exchange testExchange() {
        return new TopicExchange(exchangeName, true, false); //Example of 'type: topic' exchange
    }

    @Bean
    public Binding binding(Queue testQueue, Exchange testExchange) {
        return BindingBuilder.bind(testQueue).to(testExchange).with("new.key").noargs();
    }

    @RabbitListener(queues = queueName)
    public void listen(String message) {
        System.out.format("Received NEW message from '%s' : %s;\n", queueName, message);
    }

    public static void main(String[] args) {
        SpringApplication.run(NewTopicConsumerApplication.class, args);
    }

}
