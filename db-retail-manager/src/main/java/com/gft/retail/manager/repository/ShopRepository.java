package com.gft.retail.manager.repository;

import org.springframework.data.repository.CrudRepository;

import com.gft.retail.manager.model.Shop;

public interface ShopRepository extends CrudRepository<Shop, Integer> {

	Shop findByName(String name);
	
}
