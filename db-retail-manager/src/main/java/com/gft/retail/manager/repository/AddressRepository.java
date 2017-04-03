package com.gft.retail.manager.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gft.retail.manager.model.Address;
import com.gft.retail.manager.model.Shop;

/**
 * Interface to handle all Address database queries
 * 
 * @author Roberto Salazar - GFT
 */
public interface AddressRepository extends CrudRepository<Address, Integer> {

	@Query("select a from Address a")
	List<Address> findActiveAddress();
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select a from Address a where a.shop = :shop")
	Address findByShop(@Param("shop") Shop shop);
	
}
