package com.qacg.dealers.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qacg.dealers.api.util.Constants;

/**
 * @author Juan Pratz
 * @version 1.0.0
 * @since 16.12.2022
 */
@RestController
@RequestMapping(Constants.Api.PATH + "status")
public class ServiceStatusController {

	@GetMapping
	@PreAuthorize("hasAnyAuthority('root', 'admin')")
	public ResponseEntity<String> getStatus() {
		String str = "It is running";
		return ResponseEntity.ok().body(str);
	}
	
}
