package com.example.contactsproject.service.email;

import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(EmailCustomService.class);

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
            logger.error(ex.getMessage());
        }
    }

    public String getContentFromTemplate(Map<String, String> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfig.getTemplate("email-template.flth"), model));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return content.toString();
    }

}
