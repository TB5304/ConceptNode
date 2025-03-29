package com.testbook.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testbook.base.controller.BaseController;
import com.testbook.user.entity.UserEntity;
import com.testbook.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<String, UserEntity, UserService> {

	@GetMapping("/test")
	public static String testIt() {
		return "Test Success";
	}
	protected UserController(UserService service) {
		super(service);
	}
	
}
