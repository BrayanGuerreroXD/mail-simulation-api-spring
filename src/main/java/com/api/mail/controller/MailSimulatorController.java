package com.api.mail.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.mail.dto.MessageDto;
import com.api.mail.service.MailService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/mail")
@AllArgsConstructor
public class MailSimulatorController {
    public static Log log = LogFactory.getLog(MailSimulatorController.class);
    private final MailService mailService;
    
    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendMail(@RequestBody MessageDto messageRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            response.put("Message", "Mail sent successfully");
            mailService.sendMail(messageRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            response.put("Message", extractErrorMessage(e));
            log.error("Error sending mail: " + e.getMessage());
            return new ResponseEntity<>(response, e.getStatusCode());
        } catch (RuntimeException e) {
            log.error("Error sending mail: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    private String extractErrorMessage(ResponseStatusException e) {
        return e.getReason() != null ? e.getReason().replaceFirst("\\d{3}\\s[a-zA-Z_]+\\s", "").replace("\"", "") : e.getMessage();
    }
}