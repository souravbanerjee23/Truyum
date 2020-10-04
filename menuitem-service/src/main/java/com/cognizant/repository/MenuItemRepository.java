package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.model.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

}
