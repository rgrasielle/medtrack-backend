package com.projetoestoque.Estoque.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.projetoestoque.Estoque.entities.Product;
import com.projetoestoque.Estoque.repositories.ProductRepository;

@Service
public class ScheduledEmailService {
	
	@Autowired
    private EmailService emailService;

    @Autowired
    private ProductRepository productRepository;

    // Envia email automaticamente para os usuários cujo medicamento está perto de terminar
    
 // Envia email automaticamente para os usuários cujo medicamento está perto de terminar
    
    @Scheduled(cron = "0 0 8 * * ?")  // Executa todos os dias às 8h00
    public void sendExpirationEmails() {
        List<Product> products = productRepository.findAll();
        
        for (Product product : products) {
            // Verificação de necessidade de envio do email
            if (isEmailNeeded(product)) {
                String subject = "Aviso: Seu medicamento está prestes a terminar!";
                String text = String.format("Olá, seu produto '%s' vai terminar em %d dias. Não se esqueça de repor.",
                        product.getName(), calcularDiasRestantes(product));

                emailService.sendEmail(product.getUser().getEmail(), subject, text);
            }
        }
    }

    // Método para verificar se o envio de email é necessário
    private boolean isEmailNeeded(Product product) {
        LocalDate dataTermino = product.calcularDataTermino();
        long diasRestantes = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), dataTermino);
        return diasRestantes <= product.getDaysBeforeNotification(); 
    }

    // Método para calcular os dias restantes
    private long calcularDiasRestantes(Product product) {
        LocalDate dataTermino = product.calcularDataTermino();
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), dataTermino);
    }

}
