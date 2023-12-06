package com.beta.replyservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ReplyController {

	@Autowired
	private ReplyService stringReplyService;



	public ReplyController(ReplyService stringReplyService) {
		this.stringReplyService = stringReplyService;
	}

	@GetMapping("/reply/{inputString}")
	public ResponseEntity<?> reply(@PathVariable String inputString) {
		try {
			return ResponseEntity.ok().body(new ReplyMessage(stringReplyService.applyRules(inputString, "")));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Invalid input"));
		}

	}

	@GetMapping("/v2/reply/{rule}-{inputString}")
	public ResponseEntity<?> v2Reply(@PathVariable String rule, @PathVariable String inputString) {
		try {
			String result = stringReplyService.applyRules(inputString, rule);
			return ResponseEntity.ok().body(new ReplyMessage(result));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Invalid input"));
		}
	}
}