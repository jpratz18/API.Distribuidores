package com.qacg.dealers.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.qacg.dealers.api.dto.LoginRequestDTO;
import com.qacg.dealers.api.dto.LoginResponseDTO;
import com.qacg.dealers.api.dto.UserShortDTO;
import com.qacg.dealers.api.security.JwtUtils;
import com.qacg.dealers.api.security.UserDetailsImpl;
import com.qacg.dealers.api.service.AuthenticationService;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Override
	public LoginResponseDTO signin(LoginRequestDTO loginRequestDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		loginResponseDTO.setAccessToken(jwt);

		UserShortDTO userShortDTO = new UserShortDTO();

		userShortDTO.setId(userDetails.getId());
		userShortDTO.setName(userDetails.getName());
		userShortDTO.setEmail(userDetails.getUsername());
		userShortDTO.setAvatar(
				userDetails.getAvatar() != null ? userDetails.getAvatar() : "assets/images/avatars/brian-hughes.jpg");
		
		loginResponseDTO.setUser(userShortDTO);
		loginResponseDTO.setAccessToken(jwt);

		return loginResponseDTO;
	}

}
