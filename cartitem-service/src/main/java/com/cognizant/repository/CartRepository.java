package com.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
	public List<Cart> findByUserIdAndMenuItemId(long userId,long menuItemId);
	public List<Cart> findByUserId(long userId);
}
