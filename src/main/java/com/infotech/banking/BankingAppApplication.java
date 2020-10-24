package com.infotech.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}
	
	
	// Java Configuration for Gmail
	
	/*
	 * @Bean public JavaMailSender javaMailService() { JavaMailSenderImpl
	 * javaMailSender = new JavaMailSenderImpl();
	 * 
	 * javaMailSender.setHost("smtp.gmail.com"); javaMailSender.setPort(587);
	 * 
	 * javaMailSender.setJavaMailProperties(getMailProperties());
	 * javaMailSender.setUsername("xxx@gmail.com");
	 * javaMailSender.setPassword("xxx");
	 * 
	 * return javaMailSender; }
	 * 
	 * private Properties getMailProperties() { Properties properties = new
	 * Properties(); properties.setProperty("mail.transport.protocol", "smtp");
	 * properties.setProperty("mail.smtp.auth", "true");
	 * properties.setProperty("mail.smtp.starttls.enable", "true");
	 * properties.setProperty("mail.debug", "true");
	 * properties.setProperty("mail.smtp.ssl.enable","true");
	 * properties.setProperty("mail.test-connection","true"); return properties; }
	 */

}
