package com.qacg.dealers.api.util;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Constants implements Serializable {

	private static final long serialVersionUID = -4662536562068469188L;

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public abstract static class Api implements Serializable {

		private static final long serialVersionUID = 4275056425465307035L;
		
		public static final String TOKEN_TYPE = "Bearer";
		public static final String PATH = "/api/";

	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public abstract static class ValidationMessage implements Serializable {

		private static final long serialVersionUID = 4065241632909696744L;
		
		public static final String REQUIRED = "Required value";
		
		public static final String INVALID_FORMAT = "Invalid format";

	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public abstract static class ResponseMessage implements Serializable {
	
		private static final long serialVersionUID = 6625317854166974069L;

		public static final String SUCCESS = "Succesful transaction";
		
	}

}
