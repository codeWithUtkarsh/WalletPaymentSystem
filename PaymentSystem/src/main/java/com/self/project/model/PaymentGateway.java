package com.self.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_gateway")
public class PaymentGateway {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paymentGatewayId;
	
	@Column(name = "status", nullable = false)
	private PaymentStatus status;
	
	@Column(name = "payment_info", nullable = false)
	private String transactionId;
	
	@Column(name = "user_id", nullable = false)
	private Integer userId;
	
	public PaymentGateway() {
		super();
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Integer getPaymentGatewayId() {
		return paymentGatewayId;
	}

	public void setPaymentGatewayId(Integer paymentGatewayId) {
		this.paymentGatewayId = paymentGatewayId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
