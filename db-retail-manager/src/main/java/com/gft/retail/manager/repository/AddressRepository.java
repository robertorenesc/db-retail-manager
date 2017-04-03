package com.gft.retail.manager.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gft.retail.manager.model.Address;
import com.gft.retail.manager.model.Shop;

public interface AddressRepository extends CrudRepository<Address, Integer> {

	@Query("select a from Address a where a.shop = :shop and a.active = true order by a.id desc")
	List<Address> findActiveAddressByShop(@Param("shop") Shop shop);
	
	@Query("select a from Address a where a.active = true")
	List<Address> findActiveAddress();
	
	@Modifying(clearAutomatically = true)
	@Query("update Address a set a.active = false where a.shop.id = :shopId")
	void inactivateActualAddress(@Param("shopId") BigDecimal shopId);
	
}
