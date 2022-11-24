package com.qacg.dealers.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Commons {

	public static final String passwordEncoder(String rawPassword) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(rawPassword);
	}

	public static boolean passwordMatcher(String rawPassword, String encodedPassword) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(rawPassword, encodedPassword);
	}


}
