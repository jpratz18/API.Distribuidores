package com.qacg.dealers.api.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Data
public class UserShortDTO implements Serializable {
	
	private static final long serialVersionUID = -3084279043042625871L;

	private Long id;
	
	private String name;
	
	private String email;
		
	private String avatar;
	
}

