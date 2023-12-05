package com.beta.replyservice;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReplyController.class)
class RestServiceApplicationTest {

	@MockBean
	private ReplyService stringReplyService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void reply_ValidInput_ReturnsOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/reply/helloworld"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value("helloworld"));
	}

	@Test
	void v2Reply_ValidInput_ReturnsOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/11-helloworld"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value("helloworld"));
	}

	@Test
	void v2Reply_InvalidRule_ReturnsBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/13-helloworld"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Invalid input"));
	}
}
