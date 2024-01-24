package com.api.mail.service.impl;

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.mail.dto.MessageDto;
import com.api.mail.service.MailService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private static final Log log = LogFactory.getLog(MailServiceImpl.class);
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    @Override
    public void sendMail(MessageDto messageRequest) {
        validateMessageRequest(messageRequest);
        log.info(messageRequest.toString());
    }

    private void validateMessageRequest(MessageDto messageRequest) {
        requireNonEmpty(messageRequest.getEmail(), "email");
        requireNonEmpty(messageRequest.getPlate(), "plate");
        requireNonEmpty(messageRequest.getMessage(), "message");
        requireNonEmpty(messageRequest.getParkingName(), "parkingName");

        if (!isValidEmail(messageRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The 'email' field must contain a valid email address.");
        }
    }

    private void requireNonEmpty(String value, String fieldName) {
        if (isNullOrEmpty(value)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The field '" + fieldName + "' must not be empty.");
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
