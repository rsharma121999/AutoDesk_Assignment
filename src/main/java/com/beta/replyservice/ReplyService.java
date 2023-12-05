package com.beta.replyservice;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    /*@Bean
    public ReplyService getReplyService(){
        ReplyService replyService = new ReplyService();
        return replyService;
    }*/

    public String applyRules(String input, String rule) {
        for (char ruleChar : rule.toCharArray()) {
            switch (ruleChar) {
                case '1':
                    input = new StringBuilder(input).reverse().toString();
                    break;
                case '2':
                    input = encodeMD5(input);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid rule: " + ruleChar);
            }
        }
        return input;
    }

    private String encodeMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encoding MD5", e);
        }
    }
}
