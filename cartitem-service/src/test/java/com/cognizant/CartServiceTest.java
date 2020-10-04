package com.cognizant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.client.MenuFeignClient;
import com.cognizant.dto.CartItem;
import com.cognizant.dto.CartList;
import com.cognizant.exception.ItemNotFoundException;
import com.cognizant.exception.MenuItemsNotLoadException;
import com.cognizant.model.Cart;
import com.cognizant.model.MenuItem;
import com.cognizant.repository.CartRepository;
import com.cognizant.service.CartService;

@SpringBootTest

class CartServiceTest {

	@MockBean

	private CartRepository repository;

	@Autowired

	private CartService cartService;

	@MockBean

	private MenuFeignClient client;

	@Test

	public void testShowAllMenuItem() throws MenuItemsNotLoadException {

		List<MenuItem> list = new ArrayList<>();

		try {

			list.add(new MenuItem(100L, "Sandwich", 200, true, new SimpleDateFormat("dd-MM-yyyy").parse("20-09-2020"),

					"Starter", true));

		} catch (ParseException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		ResponseEntity<List<MenuItem>> re = ResponseEntity.status(HttpStatus.OK)

				.body(list);

		when(client.getMenuItemListCustomer()).thenReturn(re);

		assertEquals(cartService.showAllMenuItem().size(), list.size());

	}

	@Test

	void testAddToCart() {

		Cart cart = new Cart(1L, 500L);

		List<Cart> cartList = new ArrayList<>();

		cartList.add(cart);

		when(repository.findByUserIdAndMenuItemId(1L, 500L)).thenReturn(cartList);

		assertEquals("Item Successfully Added to Cart", cartService.addToCart(1L, 500L));

	}

	@Test

	void testDeleteCartItem() throws ItemNotFoundException {

		Cart cart = new Cart(1L, 500L);

		List<Cart> cartList = new ArrayList<>();

		cartList.add(cart);

		when(repository.findByUserIdAndMenuItemId(1L, 500L)).thenReturn(cartList);

		cartService.deleteCartItem(1l, 500l);

		assertEquals("SuccessFully Deleted the item from cart.", cartService.deleteCartItem(1l, 500l));

	}

	@Test

	void testGetAllFromCart() {

		List<MenuItem> list = new ArrayList<>();

		try {

			MenuItem menuItem = new MenuItem(500L, "Chicken Tandoori", 200, true,

					new SimpleDateFormat("dd-MM-yyyy").parse("20-09-2020"), "Starter", true);

			list.add(menuItem);

			Cart cart = new Cart(1L, 500L, 2);

			List<Cart> cartList = new ArrayList<>();

			cartList.add(cart);

			CartItem c = new CartItem(menuItem.getName(), menuItem.getPrice(), menuItem.getCategory(),

					menuItem.isFreeDelivery(), cart.getCount());

			List<CartItem> ci = new ArrayList<>();

			ci.add(c);

			CartList cartList1 = new CartList(ci, (long) (menuItem.getPrice() * cart.getCount()));

			when(repository.findByUserId(1L)).thenReturn(cartList);

			assertEquals(cartList1, cartService.getAllFromCart(1l));

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

}
