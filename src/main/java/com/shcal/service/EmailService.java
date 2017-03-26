package com.shcal.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.shcal.model.Employee;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailService 
{

	@Autowired
    private JavaMailSenderImpl javaMailSender;
	
    char c = 34; //Anführungszeichen
    char lf = 10; // Linefeed
    
 
    public void sendActivationMail(Employee employee, String url) {

    	String fromEmail = "test@web.de" ;
        String toEmail = employee.getEmail();
        String emailSubject = "Registration" ;
        //char c = 34; //Anführungszeichen
        String emailBody = String.format("Dear " + employee.getFullName()+"," + lf
        		+ "thanks for choosing the SharedCalendar. Have a nice day!" + lf 
        		+ "Please click the following link to activate your account." + lf 
        		+ url + lf
        		+ "Greetings," + lf
        		+ "Your administration");       		
        		 
        		//+ "<a href="+c+url+c+">Activation Link</a>");       
     
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
          MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
     
          helper.setFrom(fromEmail);
          helper.setTo(toEmail);
          helper.setSubject(emailSubject);
          helper.setText(emailBody);
     
          /*
            uncomment the following lines for attachment FileSystemResource
            file = new FileSystemResource(&quot;attachment.jpg&quot;);
            helper.addAttachment(file.getFilename(), file);
           */
     
          javaMailSender.send(mimeMessage);
          
        } catch (MessagingException e) {
          e.printStackTrace();
        }
       
    }
    
    public void sendPasswordMail(Employee employee, String rawPassword) {

    	String fromEmail = "stefan.gamer@web.de" ;
        String toEmail = employee.getEmail();
        String emailSubject = "Password Change" ;
        String emailBody = String.format("Dear " + employee.getFullName()+"," + lf
        		+ "Your new password is: " + rawPassword + lf
        		+ "Greetings," + lf
        		+ "Your Administration");
    
     
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
          MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
     
          helper.setFrom(fromEmail);
          helper.setTo(toEmail);
          helper.setSubject(emailSubject);
          helper.setText(emailBody);
     
          /*
            uncomment the following lines for attachment FileSystemResource
            file = new FileSystemResource(&quot;attachment.jpg&quot;);
            helper.addAttachment(file.getFilename(), file);
           */
     
          javaMailSender.send(mimeMessage);
          
        } catch (MessagingException e) {
          e.printStackTrace();
        }
       
    }
}




 