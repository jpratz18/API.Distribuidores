package com.qacg.dealers.api.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.qacg.dealers.api.util.Constants;

import lombok.Data;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Data
public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = -3084279043042625871L;

	private Long id;
	
	@NotNull(message = Constants.ValidationMessage.REQUIRED)
	private String name;
	
	@NotNull(message = Constants.ValidationMessage.REQUIRED)
	private String lastname;
	
	@Email(message = Constants.ValidationMessage.INVALID_FORMAT)
	private String email;
	
	@NotNull(message = Constants.ValidationMessage.REQUIRED)
	private String password;
	
	private Boolean enabled;
	
	private Long retries;
		
	@NotNull(message = Constants.ValidationMessage.REQUIRED)
	private String phone;
	
	private String profileImage;
	
	private Date created;
	
	private Date updated;
	

}

