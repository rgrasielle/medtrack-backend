package com.projetoestoque.Estoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String remetente;

	public String sendEmail(String to, String subject, String text) {
		
		try {
			
			SimpleMailMessage message = new SimpleMailMessage();

			message.setFrom(remetente);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);

			javaMailSender.send(message);

			return "Email enviado";
	
		} catch (Exception e) {
			return "Erro ao tentar enviar email " + e.getLocalizedMessage();
		}
		
	}

}
