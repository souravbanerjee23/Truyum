package com.cognizant.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.dto.CartList;
import com.cognizant.exception.ItemNotFoundException;
import com.cognizant.exception.MenuItemsNotLoadException;
import com.cognizant.model.MenuItem;
import com.cognizant.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartControler {
	@Autowired
	private CartService cartService;
	@GetMapping("/menus")
	public List<MenuItem> getAllMenuItem() throws MenuItemsNotLoadException{
		return cartService.showAllMenuItem();
	}
	@GetMapping("/{userId}")
	public CartList getAllFromCart(@PathVariable long userId) {
		return cartService.getAllFromCart(userId);
	}
	@PostMapping("/addtocart/{userId}/{menuItemId}")
	public String addToCart(@PathVariable long userId,@PathVariable long menuItemId) {
		return cartService.addToCart(userId,menuItemId);
	}
	@DeleteMapping("/delete/{userId}/{menuItemId}")
	public String deleteFromCart(@PathVariable long userId,@PathVariable long menuItemId) throws ItemNotFoundException {
		return cartService.deleteCartItem(userId, menuItemId);
	}
	
}
