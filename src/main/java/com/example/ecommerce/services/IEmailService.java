package com.example.ecommerce.services;

import com.example.ecommerce.domain.models.Order;
import com.example.ecommerce.domain.models.Product;
import com.example.ecommerce.domain.models.User;
import jakarta.mail.MessagingException;

import java.util.Map;

public interface IEmailService {
    void sendOrderConfirmationEmail(User user, Order order, Map<Long, Integer> purchasedQuantities) throws MessagingException;
    void sendOrderStatusUpdateEmail(User user, Order order) throws MessagingException;
    void sendNewOrderNotificationToAdmin(User admin, Order order) throws MessagingException;
    void sendPasswordResetEmail(User user, String newPassword) throws MessagingException;
    void sendOutOfStockNotificationToAdmin(User admin, Product product) throws MessagingException;
}
