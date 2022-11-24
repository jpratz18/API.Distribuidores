package com.qacg.dealers.api.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Data
public class LoginResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 8553785606398169756L;

	private String accessToken;
	
    private UserShortDTO user;

}
