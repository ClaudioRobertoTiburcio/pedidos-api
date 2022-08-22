package com.pedidos.api.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "_order") //Aparentemente "order" é uma palavra reservada do postresql
public class Order {
	
	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
	@Column(name = "id_order", updatable = false, nullable = false)
	private UUID id;	
	
	@Column(nullable = false)
	//@NotEmpty(message = "Field NUMBER is Required")
	private int number;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	//@NotEmpty(message = "Field DATE is Required")
	private Date date;
	
	@Column(nullable = false)
	//@NotEmpty(message = "Field PERCENTUAL_DISCOUNT is Required")
	private double percentualDiscount;
	
	private double totalValue;
}
