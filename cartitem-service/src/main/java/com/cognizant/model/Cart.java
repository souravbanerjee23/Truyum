package com.cognizant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity

@Data
@AllArgsConstructor
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private long userId;
	private long menuItemId;
	private int count;

	public Cart(long userId, long menuItemId, int count) {
		super();
		this.userId = userId;
		this.menuItemId = menuItemId;
		this.count = count;
	}

	public Cart(long userId, long menuItemId) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		this.menuItemId = menuItemId;
	}

}
