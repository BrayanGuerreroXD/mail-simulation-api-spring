package com.api.mail.service;

import com.api.mail.dto.MessageDto;

public interface MailService {
    void sendMail(MessageDto messageRequest);
}