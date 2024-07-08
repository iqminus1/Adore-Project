package uz.pdp.adoreproject.service;

public interface MailSenderService {
    void sendMessage(String email, String message,String subject);
}
