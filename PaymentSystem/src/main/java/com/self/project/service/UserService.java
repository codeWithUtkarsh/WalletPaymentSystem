package com.self.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.self.project.model.User;
import com.self.project.repo.UserRepo;

@Service
public class UserService
{
	@Autowired
	private UserRepo userRepo;
	
	public User createUser(User user)
	{
		return userRepo.save(user);
	}
	
	public Double getWalletAmmountByUserId(Integer userId)
	{
		Optional<User> fetchedUser = userRepo.findById(userId);
		if(fetchedUser.isPresent()) {
			return fetchedUser.get().getWalletBalance();
		}
		return null;
	}

	public User rechargeWallet(Integer userId, Double balance)
	{
		Optional<User> u = userRepo.findById(userId);
		if(u.isPresent())
		{
			u.get().setWalletBalance(u.get().getWalletBalance() + balance);
			return userRepo.save(u.get());
		}
		return null;
	}
	
	public User deductFromWallet(Integer userId, Double balance)
	{
		Optional<User> u = userRepo.findById(userId);
		if(u.isPresent())
		{
			u.get().setWalletBalance(u.get().getWalletBalance() - balance);
			return userRepo.save(u.get());
		}
		return null;
	}
}
