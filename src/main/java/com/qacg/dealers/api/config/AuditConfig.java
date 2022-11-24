package com.qacg.dealers.api.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Configuration
public class AuditConfig implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {

		SecurityContext ctx = SecurityContextHolder.getContext();

		if (ctx == null || ctx.getAuthentication() == null || ctx.getAuthentication().getPrincipal() == null) {
			return Optional.empty();
		}

		Object principal = ctx.getAuthentication().getPrincipal();

		if (principal.getClass().isAssignableFrom(Long.class)) {
			return Optional.of((long) principal);
		} else {
			return Optional.empty();
		}
	}
}