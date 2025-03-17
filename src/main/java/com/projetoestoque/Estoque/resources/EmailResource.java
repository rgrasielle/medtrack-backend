package com.projetoestoque.Estoque.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetoestoque.Estoque.services.EmailService;

@RestController
@RequestMapping("/email")
public class EmailResource {
	
	@Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
    	
        emailService.sendEmail(to, subject, text);
        return "E-mail enviado com sucesso!";
    }

}
