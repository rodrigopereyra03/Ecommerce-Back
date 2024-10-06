package com.example.ecommerce.services.impl;

import com.example.ecommerce.domain.models.Order;
import com.example.ecommerce.domain.models.User;
import com.example.ecommerce.services.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendOrderConfirmationEmail(User user, Order order) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Confirmación de Pedido");
            helper.setText(buildEmailContent(user, order), true);

            FileSystemResource resource = new FileSystemResource(new File("C:/Users/Usuario/Documents/Rodrigo/CASAS.PNG"));
            helper.addInline("logo", resource);

            mailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    private String buildEmailContent(User user, Order order) {
        StringBuilder content = new StringBuilder();
        content.append("<html><body>");
        content.append("<img src='cid:logo' alt='Logo de la Empresa' style='width:200px;height:auto;'><br><br>");
        content.append("Hola ").append(user.getFirstName()).append(",<br><br>");
        content.append("¡Gracias por tu pedido!<br>");
        content.append("ID del Pedido: ").append(order.getId()).append("<br>");
        content.append("Monto: $").append(order.getAmount()).append("<br><br>");
        content.append("Tu pedido será procesado pronto.<br><br>");
        content.append("Saludos cordiales,<br>");
        content.append("CASAS FRIO - CALOR");
        content.append("</body></html>");
        return content.toString();
    }
}
