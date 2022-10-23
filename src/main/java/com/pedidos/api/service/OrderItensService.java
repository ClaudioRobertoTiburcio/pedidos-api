package com.pedidos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pedidos.api.exception.ItemNotFoundException;
import com.pedidos.api.exception.OrderNotFoundException;
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

		Optional<Item> itemOpt = itemRepository.findById(orderItens.getItemId());

		Item item = itemOpt.orElseThrow(() -> {
			throw new ItemNotFoundException("Item não encontrado!");
		});

		orderItens.setId(null);
		orderItens.setOrder(idOrder);

		orderItens.setTotalValue(item.getValue() * orderItens.getQuantity());
		return orderItensRepository.save(orderItens);
	}

	public OrderItens findByOrderAndId(UUID idOrder, UUID orderItens) {
		return orderItensRepository.findByOrderAndId(idOrder, orderItens);
	}

	public OrderItens update(UUID idOrder, UUID idOrderItens, OrderItens orderItens) {

		Optional<Item> itemOpt = itemRepository.findById(orderItens.getItemId());

		Item item = itemOpt.orElseThrow(() -> {
			throw new ItemNotFoundException("Item não encontrado");
		});

		OrderItens newOrderItens = findByOrderAndId(idOrder, idOrderItens);
		newOrderItens.setItemId(item.getId());
		newOrderItens.setQuantity(orderItens.getQuantity());

		newOrderItens.setTotalValue(item.getValue() * newOrderItens.getQuantity());
		return orderItensRepository.save(newOrderItens);
	}

	public void delete(UUID idOrder, UUID idOrderItens) {
		orderItensRepository.deleteById(orderItensRepository.findByOrderAndId(idOrder, idOrderItens).getId());
	}

	public List<OrderItens> findByAllByOrder(UUID idOrder) {
		return orderItensRepository.findAllByOrder(idOrder);
	}

	public List<OrderItens> closeOrder(UUID idOrder, Order order) {
		Optional<Order> newOrderOpt = orderRepository.findById(idOrder);

		Order newOrder = newOrderOpt.orElseThrow(() -> {
			throw new OrderNotFoundException("Ordem não existente!");
		});

		newOrder.setPercentualDiscount(order.getPercentualDiscount());

		List<OrderItens> orderItens = orderItensRepository.findAllByOrder(idOrder);

		double finalValue = 0.0;
		for (OrderItens orderItem : orderItens) {

			Optional<Item> itemOpt = itemRepository.findById(orderItem.getItemId());

			Item item = itemOpt.orElseThrow(() -> {
				throw new ItemNotFoundException("Item não encontrado");
			});

			if (item.getType() == 'P') {
				finalValue += (item.getValue() * orderItem.getQuantity()) * order.getPercentualDiscount() / 100;
			} else if(item.getType() == 'S') {
				finalValue += item.getValue() * orderItem.getQuantity();
			}
		}
		newOrder.setTotalValue(finalValue);
		orderRepository.save(newOrder);
		return orderItens;
	}

}
