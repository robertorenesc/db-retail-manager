package com.gft.retail.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gft.retail.manager.model.Shop;

public interface ShopRepository extends CrudRepository<Shop, Integer> {

	Shop findByName(String name);
	
	@Query("select s from Shop as s inner join s.address as a where a.active = true order by s.name")
	List<Shop> findAllWithActiveAddress();
	
}
