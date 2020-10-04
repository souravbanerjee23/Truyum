package com.cognizant.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.model.MenuItem;

import feign.Headers;
@Headers("Content-Type: application/json")
//@FeignClient(url="${feign.url}")
@FeignClient(name="menuitem-service" , url="${MENU_SERVICE:http://localhost:8081/menuitems}")
public interface MenuFeignClient {
	@GetMapping
	public ResponseEntity<List<MenuItem>> getMenuItemListCustomer();
	@GetMapping("/{id}")
	public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id);
}
