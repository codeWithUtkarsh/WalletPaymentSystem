package com.self.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.self.project.model.PaymentGateway;

@Repository
public interface PaymentGatewayRepo extends JpaRepository<PaymentGateway, Integer> {
	
	public PaymentGateway findByUserIdAndTransactionId(Integer userId, String transactionId);
}
