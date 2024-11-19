package com.example.AttendenceApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	
	
	 @Autowired
	    private JavaMailSender mailSender;

	    public void sendNotification(String to, String status) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject("Leave Request Status Update");
	        
	        if ("Approved".equalsIgnoreCase(status)) {
	            message.setText("Congratulations! Your leave request has been approved.");
	        } else if ("Rejected".equalsIgnoreCase(status)) {
	            message.setText("Unfortunately, your leave request has been rejected.");
	        } else {
	            message.setText("Your leave request status has been updated to: " + status);
	        }

	        mailSender.send(message);
	    }

}
