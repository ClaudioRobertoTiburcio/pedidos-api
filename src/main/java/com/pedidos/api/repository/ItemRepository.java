package com.pedidos.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedidos.api.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>{

}
