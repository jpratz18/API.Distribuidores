package com.qacg.dealers.api.service;

import com.qacg.dealers.api.dto.LoginRequestDTO;
import com.qacg.dealers.api.dto.LoginResponseDTO;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
public interface AuthenticationService {

	LoginResponseDTO signin(LoginRequestDTO loginRequestDTO);
	
}
