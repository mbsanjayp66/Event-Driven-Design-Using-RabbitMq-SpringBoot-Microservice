package com.sanjay.orderService.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sanjay.orderService.dto.OrderEvent;


@Service
public class OrderProducer {
	@Value("${rabbitMQ.exchange.name}")
	private String exchange;
	
	@Value("${rabbitMQ.binding.routing.key}")
	private String orderRoutingKey;
	
	@Value("${rabbitMQ.binding.email.routing.key}")
	private String emailRoutingKey;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
	
	public void sendOrderEvent(OrderEvent orderEvent) {
		logger.info(String.format("order event sent to rabbitmq-> %s", orderEvent.toString()));
		rabbitTemplate.convertAndSend(exchange, orderRoutingKey, orderEvent);
		rabbitTemplate.convertAndSend(exchange, emailRoutingKey, orderEvent);
	}

}
