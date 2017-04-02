package com.gft.retail.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.retail.manager.model.Shop;
import com.gft.retail.manager.repository.AddressRepository;
import com.gft.retail.manager.repository.ShopRepository;

@Service
public class ShopService {

	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Shop saveShop(Shop shop) {
		
		return null;
	}
	
	public List<Shop> getAllShops() {
		
		return null;
	}
	
	public List<Shop> getNearbyShops(String latitude, String longitude) {
		
		return null;
	}
	
}
