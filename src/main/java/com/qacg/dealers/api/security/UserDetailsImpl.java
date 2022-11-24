package com.qacg.dealers.api.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.qacg.dealers.api.dto.UserDTO;

import lombok.Data;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Data
@Service
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -2798708229889103318L;

	private Long id;

	private String username;

	private String password;
	
	private String name;
	
	private String avatar;
		
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsImpl() {
		
	}

	public UserDetailsImpl(Long id, String email, String password, String name, String avatar, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = email;
		this.password = password;
		this.name = name;
		this.avatar = avatar;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(UserDTO userDTO) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		return new UserDetailsImpl(userDTO.getId(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getName() + " " + userDTO.getLastname(), userDTO.getProfileImage(), authorities);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}