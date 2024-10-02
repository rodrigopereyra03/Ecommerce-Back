package com.example.ecommerce.repositories.sql;

import com.example.ecommerce.domain.exceptions.OrderNotFoundException;
import com.example.ecommerce.domain.models.Order;
import com.example.ecommerce.repositories.IOrderRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderSQLRepository implements IOrderRepository {

    private final IOrderSQLRepository iOrderSQLRepository;

    public OrderSQLRepository(IOrderSQLRepository iOrderSQLRepository) {
        this.iOrderSQLRepository = iOrderSQLRepository;
    }

    @Override
    public Order save(Order order) {
        return iOrderSQLRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> optionalOrder = iOrderSQLRepository.findById(id);
        if(optionalOrder.isEmpty()){
            throw new OrderNotFoundException("Order not found with id: "+id);
        }
        return optionalOrder.get();
    }

    @Override
    public List<Order> findAll() {
        return iOrderSQLRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        iOrderSQLRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return iOrderSQLRepository.existsById(id);
    }

    @Override
    public List<Order> findByStatus(String status) {
        return iOrderSQLRepository.findByStatus(status);
    }

    @Override
    public List<Order> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate) {
        return iOrderSQLRepository.findByDateCreatedBetween(startDate,endDate);
    }
}
