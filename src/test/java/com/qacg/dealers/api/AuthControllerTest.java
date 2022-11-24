package com.qacg.dealers.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qacg.dealers.api.dto.LoginRequestDTO;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	private String requestJson;

	@BeforeEach
	void setup() throws JsonProcessingException {
		LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
				.email("admin@qacg.com")
				.password("T3mporal")
				.build();
		
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		this.requestJson = ow.writeValueAsString(loginRequestDTO);
	}

	@Test
	void testSignin() throws Exception {
		this.mockMVC.perform(post("/api/auth/signin").content(this.requestJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
