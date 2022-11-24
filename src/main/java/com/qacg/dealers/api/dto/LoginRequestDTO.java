package com.qacg.dealers.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.qacg.dealers.api.util.Constants;

import lombok.Builder;
import lombok.Data;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Data
@Builder
public class LoginRequestDTO implements Serializable {
	
	private static final long serialVersionUID = -1087139584253901454L;

	@NotNull(message = Constants.ValidationMessage.REQUIRED)
	private String email;
	
	@NotNull(message = Constants.ValidationMessage.REQUIRED)
	private String password;
}
