package com.laura.ex2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	@WithUserDetails
	void shouldReturnOkWhenUsingJohnUser() throws Exception {
		mockMvc.perform(get(("/hello")))
				.andExpect(status().isOk())
				.andExpect(content().string("Hola"));
	}

}
