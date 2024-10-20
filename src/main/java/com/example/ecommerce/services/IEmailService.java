package com.example.ecommerce.services;

import com.example.ecommerce.domain.models.Order;
import com.example.ecommerce.domain.models.User;
import jakarta.mail.MessagingException;

public interface IEmailService {
    void sendOrderConfirmationEmail(User user, Order order) throws MessagingException;
    void sendOrderStatusUpdateEmail(User user, Order order) throws MessagingException;
}
