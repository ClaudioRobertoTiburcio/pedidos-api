package com.pedidos.api.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedidos.api.model.Order;
import com.pedidos.api.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<Order> CreateOrder(@RequestBody Order order) {
		Order newOrder = orderService.create(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newOrder.getId())
				.toUri(); // Retorna um link para uma requisição com o id do objeto que foi criado
		return ResponseEntity.created(uri).body(newOrder);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> ReadOrder(@PathVariable UUID id) {
		Order order = this.orderService.findById(id);
		return ResponseEntity.ok().body(order);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Order> UpdateOrder(@PathVariable UUID id, @RequestBody Order order) {
		Order newOrder = orderService.update(id, order);
		return ResponseEntity.ok().body(newOrder);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> DeleteOrder(@PathVariable UUID id) {
		orderService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<Order>> ListOrders() {
		List<Order> orders = orderService.findAll();
		return ResponseEntity.ok().body(orders);
	}
}
