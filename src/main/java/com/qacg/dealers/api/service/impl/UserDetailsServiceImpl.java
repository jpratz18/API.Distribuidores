/**
 * 
 */
package com.qacg.dealers.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;
import com.qacg.dealers.api.dto.UserDTO;
import com.qacg.dealers.api.entity.User;
import com.qacg.dealers.api.exception.BusinessException;
import com.qacg.dealers.api.repository.UserRepository;
import com.qacg.dealers.api.security.UserDetailsImpl;
import com.qacg.dealers.api.service.UserService;
import com.qacg.dealers.api.util.Commons;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Slf4j
@Service
@Getter(value = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

	@Autowired
	private Mapper dozerMapper;

	@Override
	public UserDTO save(UserDTO userDTO) throws BusinessException {
		try {
			User user = new User();
			this.getDozerMapper().map(userDTO, user);

			// Encryptando password
			user.setPassword(Commons.passwordEncoder(user.getPassword()));
			user.setRetries(0l);
			user.setEnabled(Boolean.TRUE);

			user = repository.save(user);
			this.getDozerMapper().map(user, userDTO);

			return userDTO;
		} catch (DataIntegrityViolationException e) {
			log.error("User is already registered with that email: ", userDTO.getEmail());
			throw new BusinessException("User is already registered with that email: " + userDTO.getEmail());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Optional<UserDTO> getId(Long id) throws BusinessException {
		try {
			Optional<UserDTO> userDTO = repository.findById(id)
					.map(UserDTO::ofEntity);
			return userDTO;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<UserDTO> getAll() throws BusinessException {
		try {
			Iterable<User> userIterator = repository.findAll();
			List<UserDTO> userList = new ArrayList<>();

			userIterator.forEach(user -> {
				UserDTO userDTO = new UserDTO();
				this.getDozerMapper().map(user, userDTO);

				userList.add(userDTO);
			});

			return userList;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
		
		UserDTO userDTO = new UserDTO();
		this.getDozerMapper().map(user, userDTO);
			
		log.info("User found {}.", userDTO);
		return UserDetailsImpl.build(userDTO);
	}

}
