package com.example.ecommerce.services.impl;

import com.example.ecommerce.api.dto.OrderDto;
import com.example.ecommerce.api.mappers.OrderMapper;
import com.example.ecommerce.domain.Enums.OrderStatus;
import com.example.ecommerce.domain.Enums.UserRol;
import com.example.ecommerce.domain.exceptions.OrderNotFoundException;
import com.example.ecommerce.domain.exceptions.ProductNotFoundException;
import com.example.ecommerce.domain.exceptions.UserNotFoundException;
import com.example.ecommerce.domain.models.Order;
import com.example.ecommerce.domain.models.Product;
import com.example.ecommerce.domain.models.User;
import com.example.ecommerce.repositories.IOrderRepository;
import com.example.ecommerce.repositories.IProductRepository;
import com.example.ecommerce.repositories.IUserRepository;
import com.example.ecommerce.services.IEmailService;
import com.example.ecommerce.services.IOrderServices;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderServices {

    private final IOrderRepository iOrderRepository;
    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final IEmailService iEmailService;

    public OrderServiceImpl(IOrderRepository iOrderRepository, IUserRepository iUserRepository, IProductRepository iProductRepository, IEmailService iEmailService) {
        this.iOrderRepository = iOrderRepository;
        this.iUserRepository = iUserRepository;
        this.iProductRepository = iProductRepository;
        this.iEmailService = iEmailService;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto,String userEmail) throws MessagingException {
        Order order = OrderMapper.toOrder(orderDto);

        // Carga el usuario desde la base de datos
        User user = iUserRepository.findFirstByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        order.setUser(user);

        // Carga los productos desde la base de datos y configura las cantidades
        List<Product> products = orderDto.getProducts().stream().map(orderProductDto -> {
            Product product = iProductRepository.findById(orderProductDto.getId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));
            product.setQuantity(orderProductDto.getQuantity()); // Asumiendo que Product tiene un campo 'quantity'
            return product;
        }).collect(Collectors.toList());
        order.setProducts(products);

        // Calcular el total de la orden
        double totalAmount = products.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
        order.setAmount(totalAmount);

        // Asigna el estado a CREATED
        order.setStatus(OrderStatus.CREATED);
        // Configura la dirección
        order.setAddress(orderDto.getAddress());

        // Guarda la orden
        Order savedOrder = iOrderRepository.save(order);
       /* iEmailService.sendOrderConfirmationEmail(user, savedOrder);
        // Enviar email de notificación al admin
        User admin = iUserRepository.findFirstByRole(UserRol.ADMIN)
                .orElseThrow(() -> new RuntimeException("Admin user not found"));
        iEmailService.sendNewOrderNotificationToAdmin(admin, savedOrder);*/
        return OrderMapper.toOrderDTO(savedOrder);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orderList = iOrderRepository.findAll();
        return orderList.stream()
                .map(order -> {
                    OrderDto orderDto = OrderMapper.toOrderDTO(order);

                    // Calculo el total de amount sumando los productos
                    int totalAmount = order.getProducts().stream()
                            .mapToInt(product -> product.getQuantity() * product.getPrice().intValue())
                            .sum();

                    orderDto.setAmount(totalAmount);
                    return orderDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = iOrderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        OrderDto orderDto = OrderMapper.toOrderDTO(order);

        // Calculo el total de amount sumando los productos
        int totalAmount = order.getProducts().stream()
                .mapToInt(product -> product.getQuantity() * product.getPrice().intValue())
                .sum();

        orderDto.setAmount(totalAmount);
        return orderDto;
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        return null;
    }

    @Override
    public String deleteOrder(Long id) {
        if (iOrderRepository.existsById(id)){
            iOrderRepository.deleteById(id);
            return "The Order has been successfully deleted";
        }else {
            return "The Order not found with id: " + id;
        }

    }

    @Override
    public List<OrderDto> getOrdersByStatus(OrderStatus status) {
        return iOrderRepository.findByStatus(status)
                .stream()
                .map(OrderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = iOrderRepository.findByDateCreatedBetween(startDate, endDate);
        return orders.stream()
                .map(OrderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByUserEmail(String email) {
        Optional<User> userOptional = iUserRepository.findFirstByEmail(email);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            List<Order> orders = iOrderRepository.findByUser(user);
            return orders.stream().map(OrderMapper::toOrderDTO).collect(Collectors.toList());
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public OrderDto updateOrderStatus(Long id, OrderStatus status) throws MessagingException {
        Order order = iOrderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setStatus(status);
        Order savedOrder = iOrderRepository.save(order);

        iEmailService.sendOrderStatusUpdateEmail(order.getUser(), order);
        return OrderMapper.toOrderDTO(savedOrder);
    }
}
