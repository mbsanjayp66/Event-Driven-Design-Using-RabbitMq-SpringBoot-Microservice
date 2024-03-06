package com.sanjay.orderService.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjay.orderService.dto.Order;
import com.sanjay.orderService.dto.OrderEvent;
import com.sanjay.orderService.dto.Status;
import com.sanjay.orderService.publisher.OrderProducer;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	
	
	@Autowired
	private OrderProducer orderProducer;
	
	@PostMapping("/orders")
	public String placeOrder(@RequestBody Order order) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderEvent orderEvent = new OrderEvent();
		orderEvent.setStatus(Status.Pending);
		orderEvent.setOrder(order);
		orderEvent.setMessage("order is in Pending Status");
		orderProducer.sendOrderEvent(orderEvent);
		return "Order sent to the RabbitMq";
	}
}
