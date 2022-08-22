package com.pedidos.api.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItens {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
	@Column(name = "id_order_itens", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "id_order") // novamente erro de palavra reservada do postgreSQL
	private UUID order;

	@Column(name = "id_item", nullable = false)
	private UUID itemId;

	@Column(nullable = false)
	private double quantity;

	private double totalValue;

}
