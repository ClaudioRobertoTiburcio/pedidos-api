package com.pedidos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.api.model.Order;
import com.pedidos.api.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	public OrderRepository orderRepository;

	public Order create(Order order) {
		order.setId(null); 
		return orderRepository.save(order);  // Se a requisição de post receber um ID ja existente (Por mais improvavel que
											 // seja) ainda assim sera criado um novo registro
	}

	public Order findById(UUID id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.orElse(null);
	}

	public Order update(UUID id, Order order) {
		Order newOrder = findById(id);
		newOrder.setNumber(order.getNumber());
		newOrder.setDate(order.getDate());
		newOrder.setPercentualDiscount(order.getPercentualDiscount());
		newOrder.setTotalValue(order.getTotalValue());
		return orderRepository.save(newOrder);
	}

	public void delete(UUID id) {
		orderRepository.deleteById(id);
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}
}
