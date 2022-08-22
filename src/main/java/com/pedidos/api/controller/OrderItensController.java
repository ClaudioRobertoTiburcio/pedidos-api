package com.pedidos.api.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedidos.api.model.Order;
import com.pedidos.api.model.OrderItens;
import com.pedidos.api.service.OrderItensService;

@RestController
@RequestMapping("/api/orders")
public class OrderItensController {
	
	@Autowired
	public OrderItensService orderItensService;
	
	@PostMapping("/{idOrder}/items")
	public ResponseEntity<OrderItens> CreateOrderItens(@PathVariable UUID idOrder, @RequestBody OrderItens orderItens){
		OrderItens newOrderItem = orderItensService.create(idOrder, orderItens);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newOrderItem.getId())
				.toUri(); //Retorna um link para uma requisição com o id do objeto que foi criado
		return ResponseEntity.created(uri).body(newOrderItem); 
		
	}
	
	@GetMapping("/{idOrder}/items/{idOrderItens}")
	public ResponseEntity<OrderItens> ReadOrderItens(@PathVariable UUID idOrder, @PathVariable UUID idOrderItens){
		OrderItens newOrderItens = orderItensService.findByOrderAndId(idOrder, idOrderItens);
		return ResponseEntity.ok().body(newOrderItens);
	}
	
	@PutMapping("/{idOrder}/items/{idOrderItens}")
	public ResponseEntity<OrderItens> UpdateOrderItens(@PathVariable UUID idOrder, @PathVariable UUID idOrderItens, @RequestBody OrderItens orderItens){
		OrderItens newOrderItens = orderItensService.update(idOrder, idOrderItens, orderItens);
		return ResponseEntity.ok().body(newOrderItens);
	}
	
	@DeleteMapping("/{idOrder}/items/{idOrderItens}")
	public ResponseEntity<Void> DeleteOrderItens(@PathVariable UUID idOrder, @PathVariable UUID idOrderItens){
		orderItensService.delete(idOrder, idOrderItens);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{idOrder}/items")
	public ResponseEntity<List<OrderItens>> ListOrderItens(@PathVariable UUID idOrder){
		List<OrderItens> orderItens = orderItensService.findByAllByOrder(idOrder);
		return ResponseEntity.ok().body(orderItens);
	}
	
	@PatchMapping("/{idOrder}/close")
	public ResponseEntity<List<OrderItens>> CloseOrder(@PathVariable UUID idOrder, @RequestBody Order order){
		List<OrderItens> orderItens = orderItensService.closeOrder(idOrder, order);
		return ResponseEntity.ok().body(orderItens);
	}	//nas pesquisas que eu fiz não consegui encontrar um exemplo de como fazer o exemplo de retorno do teste
}
