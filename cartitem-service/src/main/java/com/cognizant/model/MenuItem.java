package com.cognizant.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuItem {
	private long id;
	private String name;
	private float price;
	private boolean actv;
	private Date dateOfLaunch;
	private String category;
	private boolean freeDelivery;

}