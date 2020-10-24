package com.infotech.banking.userserviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.infotech.banking.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	public void sendEmail(String to,String body) {

		  System.out.println("Sending email...");

		    SimpleMailMessage message = new SimpleMailMessage();
		    message.setTo(to);
		    message.setFrom("xxx@gmail.com");
		    message.setSubject("Account Created");
		    message.setText(body+"Congratulations");
		    
		    javaMailSender.send(message);

		    System.out.println("Email Sent!");

    }
	
}
