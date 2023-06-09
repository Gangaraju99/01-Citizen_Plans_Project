package com.insurance.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;



@Component
public class EmailSender {

	@Autowired
	// predefined class in springboot
	private JavaMailSender mailSender;

	public boolean sendEmail(String subject, String body, String to, File f) {

		try {

			MimeMessage mimeMsg = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg,true);
			helper.setSubject(subject);
			helper.setText(body, true);
			// multiple users
			helper.setTo(to);
			//helper.addAttachment(attachmentFilename, dataSource);
			helper.addAttachment("Plans-Info", f);
			mailSender.send(mimeMsg);

		} catch (Exception e) {
				e.printStackTrace();
		}

		return true;
	}

}
