package com.sanjay.emailService.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.sanjay.emailService.dto.OrderEvent;


@Service
public class OrderConsumer {
	private final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
	
	@RabbitListener(queues = "${rabbitMQ.queue.email}")
	public void consume(OrderEvent orderEvent) {
		logger.info(String.format("Order Event Received in EmailService => %s", orderEvent.toString()));
	}
}
