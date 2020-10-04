package com.cognizant.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.client.MenuFeignClient;
import com.cognizant.dto.CartItem;
import com.cognizant.dto.CartList;
import com.cognizant.exception.ItemNotFoundException;
import com.cognizant.exception.MenuItemsNotLoadException;
import com.cognizant.model.Cart;
import com.cognizant.model.MenuItem;
import com.cognizant.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository repository;
	@Autowired
	private MenuFeignClient client;

	@Transactional
	public List<MenuItem> showAllMenuItem() throws MenuItemsNotLoadException {
		ResponseEntity<List<MenuItem>> re = client.getMenuItemListCustomer();
		if (re.getStatusCode() == HttpStatus.OK) {
			return re.getBody();
		} else {
			throw new MenuItemsNotLoadException();
		}
	}

	@Transactional
	public String addToCart(long userId, long menuItemId) {
		List<Cart> carts = repository.findByUserIdAndMenuItemId(userId, menuItemId);
		if (!carts.isEmpty()) {
			Cart cart = carts.get(0);
			cart.setCount(cart.getCount() + 1);
			repository.save(cart);
		} else {
			repository.save(new Cart(userId, menuItemId, 1));
		}
		return "Item Successfully Added to Cart";
	}

	@Transactional
	public String deleteCartItem(long userId, long menuItemId) throws ItemNotFoundException {
		List<Cart> carts = repository.findByUserIdAndMenuItemId(userId, menuItemId);
		if (!carts.isEmpty()) {
			Cart cart = carts.get(0);
			if (cart.getCount() > 1) {
				cart.setCount(cart.getCount() - 1);
				repository.save(cart);
			} else
				repository.delete(cart);
			return "SuccessFully Deleted the item from cart.";
		} else {
			throw new ItemNotFoundException();
		}
	}

	@Transactional
	public CartList getAllFromCart(long userId) {
		List<Cart> carts = repository.findByUserId(userId);
		List<CartItem> cartList = new ArrayList<>();
		long total = 0;
		for (Cart c : carts) {
			MenuItem menuItem = client.getMenuItemById(c.getMenuItemId()).getBody();
			CartItem ci = new CartItem(menuItem.getName(), menuItem.getPrice(), menuItem.getCategory(),
					menuItem.isFreeDelivery(), c.getCount());
			total += menuItem.getPrice() * c.getCount();
			cartList.add(ci);
		}
		return new CartList(cartList, total);
	}
}
