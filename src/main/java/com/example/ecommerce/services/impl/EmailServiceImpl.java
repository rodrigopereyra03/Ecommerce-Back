package com.example.ecommerce.services.impl;

import com.example.ecommerce.domain.models.Order;
import com.example.ecommerce.domain.models.Product;
import com.example.ecommerce.domain.models.User;
import com.example.ecommerce.services.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;

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

            UrlResource headerImage = new UrlResource("http://vps-4482586-x.dattaweb.com:9000/webapp/CASAS.png");
            helper.addInline("header", headerImage);

            // Añadir la imagen del pie de página
            UrlResource footerImage = new UrlResource("http://vps-4482586-x.dattaweb.com:9000/webapp/FOOTER.png");
            helper.addInline("footer", footerImage);

            // Añadir imágenes de productos
            for (Product product : order.getProducts()) {
                if (product.getImages() != null && !product.getImages().isEmpty()) {
                    String imageUrl = product.getImages().get(0); // Usa la primera imagen de la lista
                    UrlResource productImage = new UrlResource(imageUrl);
                    helper.addInline("product-" + product.getId(), productImage);
                }
            }

                mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendOrderStatusUpdateEmail(User user, Order order) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Actualización de Estado de Orden");
            helper.setText(buildStatusUpdateEmailContent(user, order), true);
            UrlResource headerImage = new UrlResource("http://vps-4482586-x.dattaweb.com:9000/webapp/CASAS.png");
            helper.addInline("header", headerImage);
            UrlResource footerImage = new UrlResource("http://vps-4482586-x.dattaweb.com:9000/webapp/FOOTER.png");
            helper.addInline("footer", footerImage);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendNewOrderNotificationToAdmin(User admin, Order order) throws MessagingException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(admin.getEmail());
            helper.setSubject("Nueva Orden Recibida");
            helper.setText(buildNewOrderEmailContent(admin, order), true);
            UrlResource headerImage = new UrlResource("http://vps-4482586-x.dattaweb.com:9000/webapp/CASAS.png");
            helper.addInline("header", headerImage);
            UrlResource footerImage = new UrlResource("http://vps-4482586-x.dattaweb.com:9000/webapp/FOOTER.png");
            helper.addInline("footer", footerImage);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new MessagingException("Failed to send email to admin", e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendPasswordResetEmail(User user, String newPassword) throws MessagingException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Restablecimiento de Contraseña");
            helper.setText(buildPasswordResetEmailContent(user, newPassword), true);

            // Añadir imágenes en línea, si es necesario
            UrlResource headerImage = new UrlResource("http://vps-4482586-x.dattaweb.com:9000/webapp/CASAS.png");
            helper.addInline("header", headerImage);

            UrlResource footerImage = new UrlResource("http://vps-4482586-x.dattaweb.com:9000/webapp/FOOTER.png");
            helper.addInline("footer", footerImage);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
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
            String imageUrl = product.getImages() != null && !product.getImages().isEmpty() ? product.getImages().get(0) : "default-image-url.jpg";
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


    private String buildStatusUpdateEmailContent(User user, Order order) {
        StringBuilder content = new StringBuilder();
        content.append("<html><head>");
        content.append("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@500;600;700&display=swap' rel='stylesheet'>");
        content.append("<style>");
        content.append("body { font-family: 'Poppins'; font-weight: 600; }");
        content.append("</style>");
        content.append("</head><body style='font-family:\"Poppins\",font-weight: 600;'>");
        content.append("<div style='background-color:#f7f7f7;padding:20px;'>");
        content.append("<div style='max-width:600px;margin:auto;background-color:rgba(255,255,255,0.8);border:1px solid #ddd;border-radius:10px;padding:20px;'>");
        content.append("<div style='text-align:center;'>");
        content.append("<img src='cid:header' alt='Encabezado' style='width:200px;height:auto;'><br><br>");
        content.append("</div>");
        content.append("<div style='padding:15px;background-color:white;border:1px solid #ddd;border-radius:10px;text-align:center;'>");
        content.append("<h2 style='color:#333;'>Hola ").append(user.getFirstName()).append(",</h2>");
        content.append("<p>Hemos recibido el pago.").append(" Estamos preparando tu pedido.");
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

    private String buildNewOrderEmailContent(User admin, Order order) {
        StringBuilder content = new StringBuilder();
        content.append("<!DOCTYPE html>");
        content.append("<html><head>");
        content.append("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@500;600;700&display=swap' rel='stylesheet'>");
        content.append("<style>");
        content.append("body { font-family: 'Poppins', sans-serif; font-weight: 600; }");
        content.append(".content { background-color: #f7f7f7; padding: 20px; }");
        content.append(".container { max-width: 600px; margin: auto; background-color: rgba(255,255,255,0.8); border: 1px solid #ddd; border-radius: 10px; padding: 20px; }");
        content.append(".header, .footer { text-align: center; }");
        content.append(".main { padding: 15px; background-color: white; border: 1px solid #ddd; border-radius: 10px; text-align: center; }");
        content.append("</style>");
        content.append("</head><body>");
        content.append("<div class='content'>");
        content.append("<div class='container'>");
        content.append("<div class='header'>");
        content.append("<img src='cid:header' alt='Encabezado' style='width: 200px; height: auto;'><br><br>");
        content.append("</div>");
        content.append("<div class='main'>");
        content.append("<h2 style='color: #333;'>Hola Admin,</h2>");
        content.append("<p>Se ha recibido la orden numero: <strong>").append(order.getId()).append("</strong>.</p>");
        content.append("<p>Por favor, revisa los detalles en el sistema de gestión.</p>");
        content.append("<p>Saludos cordiales,<br>Casas Frio - Calor</p>");
        content.append("</div>");
        content.append("<div class='footer'>");
        content.append("<img src='cid:footer' alt='Pie de Página' style='width: 100%; height: auto; max-height: 200px;'><br><br>");
        content.append("</div>");
        content.append("</div>");
        content.append("</div>");
        content.append("</body></html>");
        return content.toString();
    }

    private String buildPasswordResetEmailContent(User user, String newPassword) {
        StringBuilder content = new StringBuilder();
        content.append("<html><head>");
        content.append("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@500;600;700&display=swap' rel='stylesheet'>");
        content.append("<style>");
        content.append("body { font-family: 'Poppins'; font-weight: 600; }");
        content.append("</style>");
        content.append("</head><body style='font-family:\"Poppins\",font-weight: 600;'>");
        content.append("<div style='background-color:#f7f7f7;padding:20px;'>");
        content.append("<div style='max-width:600px;margin:auto;background-color:rgba(255,255,255,0.8);border:1px solid #ddd;border-radius:10px;padding:20px;'>");
        content.append("<div style='text-align:center;'>");
        content.append("<img src='cid:header' alt='Encabezado' style='width:200px;height:auto;'><br><br>");
        content.append("</div>");
        content.append("<div style='padding:15px;background-color:white;border:1px solid #ddd;border-radius:10px;text-align:center;'>");
        content.append("<h2 style='color:#333;'>Hola ").append(user.getFirstName()).append(",</h2>");
        content.append("<p>Hemos recibido una solicitud para restablecer tu contraseña.</p>");
        content.append("<p><strong>Tu nueva contraseña es:</strong> ").append(newPassword).append("</p>");
        content.append("<p>Te recomendamos cambiarla después de iniciar sesión.</p>");
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
