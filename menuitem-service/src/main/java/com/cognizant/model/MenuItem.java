package com.cognizant.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menuitems")
@Data
public class MenuItem {
	@Id
	private long id;
	@NotNull(message = "Name Cannot be null")
	@NotBlank(message = "Name cannot be blank.")
	private String name;
	@NotNull(message = "Price Cannot be null")
	private float price;

	@NotNull(message = "Product Active Status Cannot be null")
	private boolean actv;


	@NotNull(message = "date of Launch Cannot be null")
	private Date dateOfLaunch;

	@NotNull(message = "Category Cannot be null")
	@NotBlank(message = "Category Cannot be Blank")
	private String category;

	@NotNull(message = "Delivery status Cannot be null")
	private boolean freeDelivery;

}