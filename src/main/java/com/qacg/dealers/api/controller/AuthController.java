package com.qacg.dealers.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qacg.dealers.api.dto.LoginRequestDTO;
import com.qacg.dealers.api.dto.LoginResponseDTO;
import com.qacg.dealers.api.service.AuthenticationService;
import com.qacg.dealers.api.util.Constants;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@RestController
@RequestMapping(Constants.Api.PATH + "auth")
public class AuthController {
	
	@Autowired
	AuthenticationService service;
	
	@PostMapping("/Authenticate")
	public ResponseEntity<LoginResponseDTO> signin(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
		return ResponseEntity.ok(service.signin(loginRequestDTO));
	}
	
}
