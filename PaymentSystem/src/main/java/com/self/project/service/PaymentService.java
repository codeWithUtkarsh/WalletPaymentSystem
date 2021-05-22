package com.self.project.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.self.project.model.PaymentGateway;
import com.self.project.model.PaymentStatus;
import com.self.project.repo.PaymentGatewayRepo;

@Service
public class PaymentService
{
	@Autowired
	private UserService userService;

	@Autowired
	private PaymentGatewayRepo gatewayRepo;

	@Autowired
	private PaymentGatewayIntegrationService gatewayIntegrationService;

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

	public PaymentGateway makePayment(Double amount, Integer userId)
	{
		try
		{
			if(validateIfAmountPresentInUserWallet(amount, userId))
			{
				String transactionId = UUID.randomUUID().toString();

				PaymentGateway paymentGateway = new PaymentGateway();
				paymentGateway.setTransactionId(transactionId);
				paymentGateway.setStatus(PaymentStatus.PENDING);
				paymentGateway.setUserId(userId);
				gatewayRepo.save(paymentGateway);

				//asynchronous call to payment gateway
				LOGGER.info("Payment send to Server at: {}, TransactionId: {}", System.currentTimeMillis(), transactionId);
				gatewayIntegrationService.makeGatewayPayment(paymentGateway, userId, amount);
				return paymentGateway;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public PaymentGateway paymentStatus(Integer userId, String transactionId)
	{
		try
		{
			return gatewayRepo.findByUserIdAndTransactionId(userId, transactionId);
		}catch (Exception e) {
			System.out.println("PaymentService.makePayment()");
		}
		return null;
	}

	private boolean validateIfAmountPresentInUserWallet(Double amount, Integer userId)
	{
		Double amountPresent = userService.getWalletAmmountByUserId(userId);
		if(amountPresent >= amount)
		{
			LOGGER.info("Payment amount validation PASSED at: {}", System.currentTimeMillis());
			return true;
		}else
		{
			LOGGER.info("Payment amount validation FAILED at: {}", System.currentTimeMillis());
			return false;
		}
	}
}
