package com.sanjay.emailService.dto;

public class OrderEvent {
	private  Status status;
	private String message;
	private Order order;
	
	public OrderEvent() {
		// TODO Auto-generated constructor stub
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderEvent(Status status, String message, Order order) {
		this.status = status;
		this.message = message;
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderEvent [status=" + status + ", message=" + message + ", order=" + order + "]";
	}
	

}

