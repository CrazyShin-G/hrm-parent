package com.erocraft.config;


import com.erocraft.util.RabbitMqConstants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 14179
 */
@Configuration
public class RabbitMqConfig {

    public static final String QUEUE_PAGE_STATIC = "queue_page_static";
    public static final String EXCHANGE_TOPICS_PAGE = RabbitMqConstants.EXCHANGE_TOPICS_PAGE;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    @Bean(EXCHANGE_TOPICS_PAGE)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_PAGE).durable(true).build();
    }

    @Bean(QUEUE_PAGE_STATIC)
    public Queue QUEUE_INFORM_SMS() {
        Queue queue = new Queue(QUEUE_PAGE_STATIC);
        return queue;
    }

    @Bean
    public Binding BINDING_QUEUE_INFORM_SMS(@Qualifier(QUEUE_PAGE_STATIC) Queue queue,@Qualifier(EXCHANGE_TOPICS_PAGE) Exchange exchange) {
        System.out.println(routingKey);
        return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
    }

}
