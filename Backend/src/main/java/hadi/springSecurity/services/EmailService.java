package hadi.springSecurity.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import hadi.springSecurity.configuration.Properties;

@Service
public class EmailService
{

	private final JavaMailSender mailSender;
	private final Properties properties;

	public EmailService(JavaMailSender mailSender, Properties properties)
	{
		this.mailSender = mailSender;
		this.properties = properties;
	}
	
	public boolean sendSimpleMail(String to, String subject, String text)
	{
		 MimeMessage message = mailSender.createMimeMessage();
		 try
		{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			try
			{
				helper.setFrom(properties.getMailFrom(),properties.getMailSignature());
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
				return false;
			}
			mailSender.send(message);
			return true;
			
		} catch (MessagingException e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
