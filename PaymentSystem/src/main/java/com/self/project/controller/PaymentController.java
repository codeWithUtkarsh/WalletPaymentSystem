package com.self.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.self.project.model.PaymentGateway;
import com.self.project.service.PaymentService;

@RestController
public class PaymentController
{

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/makePayment")
	public PaymentGateway makePayment(@RequestParam Double amount, @RequestParam Integer userId)
	{
		return paymentService.makePayment(amount, userId);
	}
	
	@GetMapping("/paymentStatus")
	public PaymentGateway paymentStatus(@RequestParam Integer userId, @RequestParam String transactionId)
	{
		return paymentService.paymentStatus(userId, transactionId);
	}
}
