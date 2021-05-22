package com.self.project.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.self.project.model.PaymentGateway;
import com.self.project.model.PaymentStatus;
import com.self.project.repo.PaymentGatewayRepo;

@Service
public class PaymentGatewayIntegrationService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PaymentGatewayRepo gatewayRepo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentGatewayIntegrationService.class);
	
	@Async
	public void makeGatewayPayment(PaymentGateway paymentGateway, Integer userId, Double amount)
	{
		try
		{
			Thread.sleep(6000);
			LOGGER.info(">> Payment initiated at Payment Gateway at:{} >>", System.currentTimeMillis());
			//deduct amount from the user's wallet
			userService.deductFromWallet(userId, amount);
			
			//update status in paymentGateway table to SUCCESS or FAILED
			Optional<PaymentGateway> currentPaymentGatewayOp = gatewayRepo.findById(paymentGateway.getPaymentGatewayId());
			if(currentPaymentGatewayOp.isPresent())
			{
				PaymentGateway currentPaymentGateway = currentPaymentGatewayOp.get();
				currentPaymentGateway.setStatus(PaymentStatus.SUCCESS);
				gatewayRepo.save(currentPaymentGateway);
				LOGGER.info(">> Payment was SUCCESSFULL at:{} >>", System.currentTimeMillis());
			}else
			{
				LOGGER.info(">> Payment was FAILED at:{} >>", System.currentTimeMillis());
			}
		}catch (Exception e) {
			System.out.println("PaymentService.makeGatewayPayment()");
		}
	}
}
