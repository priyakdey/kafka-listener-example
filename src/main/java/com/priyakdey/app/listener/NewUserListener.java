package com.priyakdey.app.listener;

import com.priyakdey.app.model.User;
import com.priyakdey.app.service.UserVerificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author Priyak Dey
 */
@Slf4j
@Component
public class NewUserListener {

    private final UserVerificationService verificationService;

    public NewUserListener(UserVerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @KafkaListener(topics = "${broker.topic-name}", groupId = "${broker.group-id}")
    public void listener(ConsumerRecord<String, User> payload) throws MessagingException, IOException {
        log.info("Received a message");
        final var user = payload.value();
        log.info("ID: {}, Username: {}, Email: {}", payload.key(), user.getName(), user.getEmail());

        verificationService.verifyUser(user);
    }

}
