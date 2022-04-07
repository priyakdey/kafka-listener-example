package com.priyakdey.app.service;

import com.priyakdey.app.model.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author Priyak Dey
 */
@Service
public class UserVerificationService {

    private final MailService mailService;

    public UserVerificationService(MailService mailService) {
        this.mailService = mailService;
    }

    public void verifyUser(User user) throws IOException, MessagingException {
        // load the static file
        final var fileResource = new ClassPathResource("static/verification-email.html");
        final var is = fileResource.getInputStream();
        final var content = new String(is.readAllBytes());

        is.close();

        // TODO: We have couple of things wrong here
        // 1. Hardcode of hostnames. Should be config driven or maybe service discovery
        // 2. API is hosted on user-reg-service, maybe we need a separate service or maybe not
        // 3. Need to also have a token with expiration
        var verificationLink = "http://localhost:8081/verfiy?id=" + user.getId();
        var userName = user.getName();

        final String emailContent =
                content
                        .replace("{name}", userName)
                        .replace("{verification-link}", verificationLink);

        var subject = "Dummy Application Newsletter Verification Email";

        mailService.send(user.getEmail(), "priyak1991dey@gmail.com", subject, emailContent, true, null);
    }
}
