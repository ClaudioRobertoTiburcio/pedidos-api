package com.pedidos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.api.model.Item;
import com.pedidos.api.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public Item create(Item item) {
		item.setId(null);	
		return itemRepository.save(item); // Se a requisição de post receber um ID ja existente (Por mais improvavel que
										  // seja) ainda assim sera criado um novo registro
	}

	public Item findById(UUID id) {
		Optional<Item> item = itemRepository.findById(id);
		return item.orElse(null);
	}

	public Item update(UUID id, Item item) {
		Item newItem = findById(id);
		newItem.setDescription(item.getDescription());
		newItem.setValue(item.getValue());
		newItem.setType(item.getType());
		return itemRepository.save(newItem);
	}


	public void delete(UUID id) {
		itemRepository.deleteById(id);
	}

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

}
