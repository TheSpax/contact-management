package com.example.contactsproject.config.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

@Configuration
public class EmailConfiguration {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.office365.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("zvonkobodanovic@outlook.com");
        javaMailSender.setPassword("Andrija2001");

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "false");
        return javaMailSender;
    }

    @Primary
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfig() {
        FreeMarkerConfigurationFactoryBean freeMarkerConfig = new FreeMarkerConfigurationFactoryBean();
        freeMarkerConfig.setTemplateLoaderPath("classpath:/templates/");
        return freeMarkerConfig;
    }
}