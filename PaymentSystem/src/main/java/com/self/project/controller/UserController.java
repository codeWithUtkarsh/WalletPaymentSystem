package com.self.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.self.project.model.User;
import com.self.project.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController
{
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/createUser")
	public User createUser(@RequestBody User user)
	{
		return userService.createUser(user);
	}
	
	@GetMapping("/getWalletAmmountByUserId")
	public Double getWalletAmmountByUserId(@RequestParam Integer userId)
	{
		return userService.getWalletAmmountByUserId(userId);
	}
	
	@PutMapping("/rechargeWallet")
	public User rechargeWallet(@RequestParam Integer userId, @RequestParam Double balance)
	{
		return userService.rechargeWallet(userId, balance);
	}
	
}
