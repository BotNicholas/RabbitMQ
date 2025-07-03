package org.botnicholas.projects.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class can be omitted if rabbit is configured via rabbitmq.conf
 */
@Configuration
public class MQConfig {
    static final String queueName = "testQueue";
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
        return BindingBuilder.bind(testQueue).to(testExchange).with("test.key").noargs();
    }
}
