package com.cognizant.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.model.MenuItem;
import com.cognizant.service.MenuItemService;

@RestController
@RequestMapping("/menuitems")
public class MenuItemController {
	@Autowired
	private MenuItemService menuItemService;

	@GetMapping
	public ResponseEntity<List<MenuItem>> getMenuItemListCustomer() {
		return menuItemService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
		return menuItemService.findById(id);
	}

	@PostMapping("/add")
	public String addMenuItem(@Valid @RequestBody MenuItem newItem) {
		menuItemService.save(newItem);
		return "Item added successfully";
	}

	@PutMapping("/update")
	public String updateMenuItem(@Valid @RequestBody MenuItem menuItem) {

		return menuItemService.updateMenuItem(menuItem);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteMenuItemById(@PathVariable Long id) {
		return menuItemService.deleteMenuItem(id);
	}

}
