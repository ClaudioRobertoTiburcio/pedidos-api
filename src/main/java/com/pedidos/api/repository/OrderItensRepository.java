package com.pedidos.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedidos.api.model.OrderItens;

@Repository
public interface OrderItensRepository extends JpaRepository<OrderItens, UUID> {
	
	OrderItens findByOrderAndId (UUID order, UUID id);
	
	void deleteByOrderAndId(UUID order, UUID id);
	
	List<OrderItens> findAllByOrder(UUID order);
	
	
}
