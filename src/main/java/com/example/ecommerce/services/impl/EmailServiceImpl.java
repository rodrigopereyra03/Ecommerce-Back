package com.example.ecommerce.services.impl;

import com.example.ecommerce.domain.models.Order;
import com.example.ecommerce.domain.models.Product;
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
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Confirmación de Pedido");
            helper.setText(buildEmailContent(user, order), true);

            FileSystemResource headerImage = new FileSystemResource(new File("C:/Users/Usuario/Documents/Rodrigo/CASAS.PNG"));
            helper.addInline("header", headerImage);

            // Añadir la imagen del pie de página
            FileSystemResource footerImage = new FileSystemResource(new File("C:/Users/Usuario/Documents/Rodrigo/FOOTER.PNG"));
            helper.addInline("footer", footerImage);

            // Añadir imágenes de productos
            for (Product product : order.getProducts()) {
                if (product.getImages() != null && !product.getImages().isEmpty()) {
                    String imagePath = product.getImages().get(0); // Usa la primera imagen
                    FileSystemResource productImage = new FileSystemResource(new File(imagePath));
                    helper.addInline("product-" + product.getId(), productImage);
                }
            }

                mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String buildEmailContent(User user, Order order) {
        StringBuilder content = new StringBuilder();
        content.append("<html><head>");
        content.append("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@500;600;700&display=swap' rel='stylesheet'>");
        content.append("<style>");
        content.append("body { font-family: 'Poppins'; font-weight: 600; }");
        content.append("ul { list-style-position: inside; padding-left: 0; text-align: center; }");
        content.append("li { display: block; text-align: center; margin: 10px 0; }");
        content.append("li img { display: block; margin: 0 auto; }");
        content.append("</style>");
        content.append("</head><body style='font-family:\"Poppins\",font-weight: 600;'>");
        content.append("<div style='background-color:#f7f7f7;padding:20px;'>");
        content.append("<div style='max-width:600px;margin:auto;background-color:rgba(255,255,255,0.8);border:1px solid #ddd;border-radius:10px;padding:20px;'>");
        content.append("<div style='text-align:center;'>");
        content.append("<img src='cid:header' alt='Encabezado' style='width:200px;height:auto;'><br><br>");
        content.append("</div>");
        content.append("<div style='padding:15px;background-color:white;border:1px solid #ddd;border-radius:10px;text-align:center;'>");
        content.append("<h2 style='color:#333;'>Hola ").append(user.getFirstName()).append(",</h2>");
        content.append("<p>¡Gracias por tu pedido!</p>");
        content.append("<p><strong>Número de orden:</strong> ").append(order.getId()).append("</p>");
        content.append("<p><strong>Importe:</strong> $").append(order.getAmount()).append("</p>");
        content.append("<p>Tu pedido esta siendo procesado.</p>");
        content.append("<p>Detalles de los productos:</p>");
        content.append("<ul>");
        for (Product product : order.getProducts()) {
            content.append("<li><img src='cid:product-").append(product.getId()).append("' alt='").append(product.getName()).append("' style='width:200px;height:auto;'><br>");
            content.append("<strong>").append(product.getName()).append("</strong>: ").append(product.getDescription()).append(" (Cantidad: ").append(product.getQuantity()).append(")</li>");
        }
        content.append("</ul>");
        content.append("<p>Saludos cordiales,<br>Casas Frio - Calor</p>");
        content.append("</div>");
        content.append("<div style='text-align:center;margin-top:20px;'>");
        content.append("<img src='cid:footer' alt='Pie de Página' style='width:100%;height:auto;max-height:200px;'><br><br>");
        content.append("</div>");
        content.append("</div>");
        content.append("</div>");
        content.append("</body></html>");
        return content.toString();
    }
}
