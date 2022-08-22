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

import com.pedidos.api.model.Item;
import com.pedidos.api.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@PostMapping
	public ResponseEntity<Item> CreateItem(@RequestBody Item item) {
		Item newItem = itemService.create(item);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newItem.getId())
				.toUri(); //Retorna um link para uma requisição com o id do objeto que foi criado
		return ResponseEntity.created(uri).body(newItem);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Item> ReadItem(@PathVariable UUID id) {
		Item item = this.itemService.findById(id);
		return ResponseEntity.ok().body(item);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Item> UpdateItem(@PathVariable UUID id, @RequestBody Item item) {
		Item newItem = itemService.update(id, item);
		return ResponseEntity.ok().body(newItem);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> DeleteItem(@PathVariable UUID id) {
		itemService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<Item>> ListItems() {
		List<Item> items = itemService.findAll();
		return ResponseEntity.ok().body(items);
	}

}
