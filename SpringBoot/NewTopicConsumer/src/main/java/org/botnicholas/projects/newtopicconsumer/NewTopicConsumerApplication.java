package org.botnicholas.projects.newtopicconsumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class NewTopicConsumerApplication {
    static final String queueName = "new";
//    static final String exchangeName = "newExchange";
    static final String exchangeName = "testExchange";

    @Bean
    public Queue testQueue() {
        //a new queue will be created for the binding with new.key routing key. Thus we have to configure dead letter exchange here manually
        return new Queue(queueName, true, false, false, Map.of("x-dead-letter-exchange",
                "dead-letter-exchange",
                "x-dead-letter-routing-key",
                "dlq-key"));
    }

    @Bean
    public Exchange testExchange() {
        return new TopicExchange(exchangeName, true, false); //Example of 'type: topic' exchange
    }

    @Bean
    public Binding binding(Queue testQueue, Exchange testExchange) {
        return BindingBuilder.bind(testQueue).to(testExchange).with("new.key").noargs();
    }

    @RabbitListener(queues = queueName, ackMode = "MANUAL")
    public void listen(String message, Channel channel, Message amqpMessage) throws IOException {
        System.out.format("Received NEW message from '%s' : %s;\n", queueName, message);

        System.out.println("Rejecting it...");
        channel.basicReject(amqpMessage.getMessageProperties().getDeliveryTag(), false);
    }

    public static void main(String[] args) {
        SpringApplication.run(NewTopicConsumerApplication.class, args);
    }

}
