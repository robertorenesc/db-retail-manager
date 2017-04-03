package com.gft.retail.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gft.retail.manager.model.Address;
import com.gft.retail.manager.model.Shop;
import com.gft.retail.manager.request.SaveShopRequest;
import com.gft.retail.manager.service.ShopService;

/**
 * Class that expose all the REST endpoints to be used
 * 
 * @author Roberto Salazar - GFT
 */
@Controller
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	/**
	 * Recevie the request and change it to store a n Shop and its Address
	 * 
	 * @param request SaveShopRequest object in Json format
	 * @return The info of the stored object
	 */
	@RequestMapping(method=RequestMethod.POST, value="/api/shops")
	public ResponseEntity<Object> storeShop(@RequestBody SaveShopRequest request) {
		try {
			Shop shop = new Shop();
			shop.setName(request.getShopName());
			Address address = new Address();
			address.setNumber(request.getShopAddressNumber());
			address.setPostCode(request.getShopAddressPostCode());
			return new ResponseEntity<>(shopService.saveShop(shop, address), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * Returns the nearby object based in the coordinates sent
	 * 
	 * @param latitude X point in the map of the sender
	 * @param longitude Y point in the map of the sender
	 * @return Shop object in Json format nearby to the sender
	 */
	@RequestMapping(method=RequestMethod.GET, value="/api/shops")
	public ResponseEntity<Object> getNearbyShops(final @RequestParam(value="latitude", required = true)double latitude, final @RequestParam(value="longitude", required = true)double longitude) {
		return new ResponseEntity<>(shopService.getNearbyShops(latitude, longitude), HttpStatus.OK);
	}
	
	/**
	 * Return all the persisted Shops with the active Address
	 * 
	 * @return Listo of Shops
	 */
	@RequestMapping(method=RequestMethod.GET, value="/api/shops/all")
	public ResponseEntity<Object> getAllShops() {
		return new ResponseEntity<>(shopService.getAllShops(), HttpStatus.OK);
	}
	
}
