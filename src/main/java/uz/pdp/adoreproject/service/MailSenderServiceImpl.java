package uz.pdp.adoreproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableAsync
public class MailSenderServiceImpl implements MailSenderService {
    private final MailSender mailSender;

    @Override
    @Async
    public void sendMessage(String email, String message, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("Adore-Project@gmail.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setTo(email);
        mailSender.send(simpleMailMessage);
    }
}
