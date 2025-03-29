package com.testbook.base.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.testbook.base.entity.BaseEntity;
import com.testbook.base.entity.BaseRequest;
import com.testbook.base.entity.BaseResponse;
import com.testbook.base.interf.BaseCrudService;

import jakarta.validation.Valid;

public abstract class BaseController<ID, REQ extends BaseEntity<ID>, S extends BaseCrudService<ID, REQ>> {

	protected final S service;

	protected BaseController(S service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<BaseResponse<REQ>> create(@Valid @RequestBody BaseRequest<REQ> request) {
		BaseResponse<REQ> response = service.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public Optional<BaseResponse<REQ>> getById(@PathVariable ID id) {
		Optional<BaseResponse<REQ>> Optional = service.findById(id);
		return Optional;
	}

	@GetMapping
	public ResponseEntity<List<BaseResponse<REQ>>> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {
		List<BaseResponse<REQ>> response = service.findAll();
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BaseResponse<REQ>> update(@PathVariable ID id, @Valid @RequestBody BaseRequest<REQ> request) {
		BaseResponse<REQ> response = service.update(id, request);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable ID id) {
		service.delete(id);
		return ResponseEntity.ok("Deteleted Successfully");
	}
}