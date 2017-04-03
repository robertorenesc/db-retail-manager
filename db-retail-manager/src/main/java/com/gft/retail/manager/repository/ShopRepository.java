package com.gft.retail.manager.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gft.retail.manager.model.Shop;

/**
 * Interface to handle all Shop database queries
 * 
 * @author Roberto Salazar - GFT
 */
public interface ShopRepository extends CrudRepository<Shop, Integer> {

	Shop findByName(String name);
	
	@Cacheable("activeShops")
	@Query("select s from Shop as s inner join s.address as a where a.active = true order by s.name")
	List<Shop> findAllWithActiveAddress();
	
	@SuppressWarnings("unchecked")
	@CacheEvict(value="activeShops",allEntries=true)
	Shop save(Shop shop);
	
}
