package com.qacg.dealers.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.qacg.dealers.api.dto.UserDTO;
import com.qacg.dealers.api.exception.BusinessException;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
public interface UserService extends UserDetailsService {
	
	UserDTO save(UserDTO userDTO) throws BusinessException;

	Optional<UserDTO> getId(Long id) throws BusinessException;

	List<UserDTO> getAll() throws BusinessException;
	
}
