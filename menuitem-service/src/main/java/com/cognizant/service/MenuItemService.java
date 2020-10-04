package com.cognizant.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cognizant.exception.MenuItemNotFoundException;
import com.cognizant.model.MenuItem;
import com.cognizant.repository.MenuItemRepository;

@Service
public class MenuItemService {

	@Autowired
	MenuItemRepository menuRepo;

	public ResponseEntity<List<MenuItem>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(menuRepo.findAll());
	}
	public ResponseEntity<MenuItem> findById(Long id) {
		MenuItem menuItem = menuRepo.findById(id).orElseThrow(()->new MenuItemNotFoundException(id));
		return ResponseEntity.status(HttpStatus.OK).body(menuItem);
	}

	public String save(MenuItem menuItem){
		 menuRepo.save(menuItem);
		 return "Item added successfully";
	}

	public String updateMenuItem(MenuItem menuItem) throws MenuItemNotFoundException{
			MenuItem item=menuRepo.findById(menuItem.getId()).orElseThrow(()->new MenuItemNotFoundException(menuItem.getId()));
		    item.setActv(menuItem.isActv());
		    item.setPrice(menuItem.getPrice());
		    item.setFreeDelivery(menuItem.isFreeDelivery());
		    menuRepo.save(item);
		    return "Updated successfully";
		
	}

	public String deleteMenuItem(Long id) {
		if (menuRepo.findById(id).isPresent()) {
			MenuItem menuItem = menuRepo.findById(id).get();
			menuRepo.delete(menuItem);
			return "Menu id " + Long.toString(id) + " deleted successfully";

		} else {
			throw new MenuItemNotFoundException(id);
		}
	}

}
