package com.pedidos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.api.model.Item;
import com.pedidos.api.model.Order;
import com.pedidos.api.model.OrderItens;
import com.pedidos.api.repository.ItemRepository;
import com.pedidos.api.repository.OrderItensRepository;
import com.pedidos.api.repository.OrderRepository;

@Service
public class OrderItensService {

	@Autowired
	public OrderItensRepository orderItensRepository;

	@Autowired
	public ItemRepository itemRepository;

	@Autowired
	public OrderRepository orderRepository;

	public OrderItens create(UUID idOrder, OrderItens orderItens) {
		orderItens.setId(null);
		orderItens.setOrder(idOrder);
		orderItens.setTotalValue(
				itemRepository.findById(orderItens.getItemId()).get().getValue() * orderItens.getQuantity());
		return orderItensRepository.save(orderItens);
	}

	public OrderItens findByOrderAndId(UUID idOrder, UUID orderItens) {
		return orderItensRepository.findByOrderAndId(idOrder, orderItens);
	}

	public OrderItens update(UUID idOrder, UUID idOrderItens, OrderItens orderItens) {
		OrderItens newOrderItens = findByOrderAndId(idOrder, idOrderItens);
		newOrderItens.setItemId(orderItens.getItemId());
		newOrderItens.setQuantity(orderItens.getQuantity());
		newOrderItens.setTotalValue(
				itemRepository.findById(orderItens.getItemId()).get().getValue() * newOrderItens.getQuantity());
		return orderItensRepository.save(newOrderItens);
	}

	public void delete(UUID idOrder, UUID idOrderItens) {
		orderItensRepository.deleteById(orderItensRepository.findByOrderAndId(idOrder, idOrderItens).getId());
	}

	public List<OrderItens> findByAllByOrder(UUID idOrder) {
		return orderItensRepository.findAllByOrder(idOrder);
	}

	public List<OrderItens> closeOrder(UUID idOrder, Order order) {
		Optional<Order> newOrder = orderRepository.findById(idOrder);
		newOrder.get().setPercentualDiscount(order.getPercentualDiscount());

		List<OrderItens> orderItens = orderItensRepository.findAllByOrder(idOrder);

		double finalValue = 0.0;
		for (OrderItens orderItem : orderItens) {

			Optional<Item> item = itemRepository.findById(orderItem.getItemId());
			if (item.get().getType() == 'P') {
				finalValue += (item.get().getValue() * orderItem.getQuantity()) * order.getPercentualDiscount() / 100;
			} else if(item.get().getType() == 'S') {
				finalValue += item.get().getValue() * orderItem.getQuantity();
			}
		}
		newOrder.get().setTotalValue(finalValue);
		orderRepository.save(newOrder.get());
		return orderItens;
	}

}
