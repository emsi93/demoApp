package com.example.demoApp.mvc.service.impl;

import com.example.demoApp.configuration.Config;
import com.example.demoApp.mvc.entity.ResetPasswordLink;
import com.example.demoApp.mvc.repository.ResetPasswordLinkRepository;
import com.example.demoApp.mvc.service.EmailServiceInterface;
import com.example.demoApp.utils.ErrorCode;
import com.example.demoApp.utils.TokenUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Service
@Data
@ConfigurationProperties(prefix = "application")
public class EmailServiceImpl implements EmailServiceInterface {

    @Value("${host}")
    private String applicationHost;

    @Value("${address.email}")
    private String userName;

    @Value("${password.email}")
    private String password;

    @Value("${mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;

    @Value("${mail.smtp.host}")
    private String mailSmtpHost;

    @Value("${mail.smtp.port}")
    private String mailSmtpPort;

    @Autowired
    private ResetPasswordLinkRepository resetPasswordLinkRepository;

    @Override
    public void sendLinkActivation(String email) {

    }

    @Override
    public void sendResetLinkPassword(String email) {
        Properties properties = initProperties(mailSmtpAuth, mailSmtpStarttlsEnable, mailSmtpHost, mailSmtpPort);
        Session session = getSession(userName, password, properties);
        try {
            String link = applicationHost + "/password/resetPassword?" + Config.TOKEN_PARAM + "=" + TokenUtil.generateToken();
            String topic = "Reset password";
            Message message = buildMessage(session, email, topic, link);
            Transport.send(message);
            log.info("Sent message successfully to " + email);
            ResetPasswordLink resetPaswordLink = new ResetPasswordLink(link, email);
            resetPasswordLinkRepository.save(resetPaswordLink);
        } catch (MessagingException e) {
            log.info(ErrorCode.generate() + " Message not sent to " + email);
            e.printStackTrace();
        }
    }

    private Message buildMessage(Session session, String email, String topic, String link) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userName));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject(topic);
        message.setText(link);
        return message;
    }

    private Properties initProperties(String mailSmtpAuth, String mailSmtpStarttlsEnable, String mailSmtpHost, String mailSmtpPort) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", mailSmtpAuth);
        props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        props.put("mail.smtp.host", mailSmtpHost);
        props.put("mail.smtp.port", mailSmtpPort);
        return props;
    }

    private Session getSession(String userName, String password, Properties props) {
        return Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });
    }
}
