package com.example.contactsproject.service.email;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
@EnableAsync
public class EmailCustomService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    Configuration fmConfig;

    @Async
    public void sendSimpleMessage(String to, Map<String, String> model) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject("Welcome to Contact Manager 😁");
            mimeMessageHelper.setFrom("zvonkobodanovic@outlook.com");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(this.getContentFromTemplate(model), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch(MessagingException ex) {

        }
    }

    public String getContentFromTemplate(Map<String, String> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfig.getTemplate("email-template.flth"), model));
        } catch (Exception ex) {

        }
        return content.toString();
    }

}
