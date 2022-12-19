package com.qacg.dealers.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qacg.dealers.api.dto.UserDTO;
import com.qacg.dealers.api.service.UserService;
import com.qacg.dealers.api.util.Constants;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@RestController
@RequestMapping(Constants.Api.PATH + "user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping
    @PreAuthorize("hasAnyAuthority('root', 'admin')")
	public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO userDTO) {
		return ResponseEntity.ok().body(service.save(userDTO));
	}
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('root', 'admin')")
	public ResponseEntity<List<UserDTO>> getAll() {
		return ResponseEntity.ok().body(service.getAll());
	}

	@GetMapping
	@PreAuthorize("hasAnyAuthority('root', 'admin')")
	public ResponseEntity<List<UserDTO>> getAll(int id) {
		return ResponseEntity.ok().body(service.getId(id));
	}	
}
