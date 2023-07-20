package com.technokratos.util.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

import static com.technokratos.consts.CoflowCommonConstants.CERTIFICATE_OUTPUT_FILE_NAME;

@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final JavaMailSender emailSender;

    @Value("${mail.username}")
    private String from;

    public void sendEmail(String to, String subject, String emailText, File attachment) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setSubject(subject);
            messageHelper.setText(emailText, true);
            messageHelper.setTo(to);
            messageHelper.setFrom(from);
            if (Objects.nonNull(attachment)) {
                messageHelper.addAttachment(CERTIFICATE_OUTPUT_FILE_NAME, attachment);
            }
        };

        emailSender.send(preparator);
    }
}

