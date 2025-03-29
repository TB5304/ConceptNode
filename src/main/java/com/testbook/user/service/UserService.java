package com.testbook.user.service;

import org.springframework.stereotype.Service;

import com.testbook.base.interf.AbstractCrudService;
import com.testbook.user.entity.UserEntity;
import com.testbook.user.repo.UserRepository;

@Service
public class UserService extends AbstractCrudService<UserEntity, String> {
	
    private final UserRepository repository;

	protected UserService(UserRepository repository) {
		super(repository);
		this.repository = repository;
	}
}