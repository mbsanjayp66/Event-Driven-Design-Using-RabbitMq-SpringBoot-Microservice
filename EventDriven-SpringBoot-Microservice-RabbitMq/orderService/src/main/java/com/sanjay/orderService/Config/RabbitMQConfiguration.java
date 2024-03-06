package com.sanjay.orderService.Config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
	
	@Value("${rabbitMQ.queue.order}")
	private String orderQueue;
	
	@Value("${rabbitMQ.queue.email}")
	private String emailQueue;
	
	@Value("${rabbitMQ.exchange.name}")
	private String orderExchange;
	
	@Value("${rabbitMQ.binding.routing.key}")
	private String orderRoutingKey;
	
	@Value("${rabbitMQ.binding.email.routing.key}")
	private String emailRoutingKey;
	
	
	@Bean
	public Queue orderQueue() {
		return new Queue(orderQueue);
	}
	
	@Bean
	public Queue emailQueue() {
		return new Queue(emailQueue);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(orderExchange);
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(orderQueue()).to(exchange()).with(orderRoutingKey);
	}
	
	@Bean
	public Binding emailBinding() {
		return BindingBuilder.bind(emailQueue()).to(exchange()).with(emailRoutingKey);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
	    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	    rabbitTemplate.setMessageConverter(converter());
	    return rabbitTemplate;
	}

}
