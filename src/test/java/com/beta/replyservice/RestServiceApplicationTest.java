package com.beta.replyservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.security.MessageDigest;

import static org.junit.jupiter.api.Assertions.assertEquals;



@ExtendWith(MockitoExtension.class)
class RestServiceApplicationTest {

	@InjectMocks
	private ReplyService stringReplyService;

	@Mock
	private MessageDigest md;


	@Test
	public void Basic_test() throws Exception {
		String input = "kbzw9ru";
		String rule = "12";
		//when(md.digest(any(byte[].class))).thenReturn("5a8973b3b1fafaeaadf10e195c6e1dd4".getBytes());

		String result = stringReplyService.applyRules(input, rule);

		assertEquals("5a8973b3b1fafaeaadf10e195c6e1dd4", result);


	}

	@Test
	public void Reverse_String_Test() throws Exception{
		String input = "kbzw9ru";
		String rule="1";
		//when(md.digest(any(byte[].class))).thenReturn("kbzw9ru".getBytes());

		String result= stringReplyService.applyRules(input,rule);

		assertEquals("ur9wzbk",result);

	}


}