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

/**
 * Class that handle all the business logic related to Shops
 * 
 * @author Roberto Salazar - GFT
 *
 */
@Service
public class ShopService {

	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private MapsAPIService mapsService;
	
	/**
	 * Method that stores a Shop entity in database
	 * 
	 * @param shop Shop to be stored
	 * @param address Address to be stored for the shop
	 * @return A Shop object with the last stored info
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Shop saveShop(Shop shop, Address address) {
		Shop existingShop = shopRepository.findByName(shop.getName());
		if(existingShop == null) {
			existingShop = shopRepository.save(shop);
		}
		existingShop.setPreviousAddress(addressRepository.findLastActive(existingShop));
		address.setShop(existingShop);
		mapsService.setCoordinates(address);
		address.setActive(true);
		addressRepository.inactivateActualAddress(existingShop.getId());
		addressRepository.save(address);
		existingShop.setAddress(addressRepository.findActiveAddressByShop(existingShop));
		return existingShop;
	}
	
	/**
	 * Method that returs all existing shops
	 * 
	 * @return List of Shop objects with active address
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Shop> getAllShops() {
		return (List<Shop>) shopRepository.findAll();
	}
	
	/**
	 * Method that get the nearby existing Shop given latitude and longitude
	 * 
	 * @param latitude Latitude of the sender
	 * @param longitude Longitude of the sender
	 * @return Shop object with the address and the distance
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Shop getNearbyShops(double latitude, double longitude) {
		List<Shop> shopList = shopRepository.findAllWithActiveAddress();
		return mapsService.getNearbyShop(latitude, longitude, shopList);
	}
	
}
