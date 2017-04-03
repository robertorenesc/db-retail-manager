package com.gft.retail.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gft.retail.manager.model.Address;
import com.gft.retail.manager.model.Shop;
import com.gft.retail.manager.repository.AddressRepository;
import com.gft.retail.manager.repository.ShopRepository;

@Service
public class ShopService {

	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private MapsAPIService mapsService;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Shop saveShop(Shop shop, Address address) {
		Shop existingShop = shopRepository.findByName(shop.getName());
		if(existingShop == null) {
			existingShop = shopRepository.save(shop);
		}
		address.setShop(existingShop);
		
		// TODO Call google API to get latitude and longitude
		mapsService.setCoordinates(address);
		
		address.setActive(true);
		addressRepository.save(address);
		existingShop.setAddress(addressRepository.findActiveAddressByShop(existingShop));
		addressRepository.inactivateActualAddress(existingShop.getId());
		return existingShop;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Shop> getAllShops() {
		return (List<Shop>) shopRepository.findAll();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Shop getNearbyShops(double latitude, double longitude) {
		List<Shop> shopList = shopRepository.findAllWithActiveAddress();
		return mapsService.getNearbyShop(latitude, longitude, shopList);
	}
	
}
