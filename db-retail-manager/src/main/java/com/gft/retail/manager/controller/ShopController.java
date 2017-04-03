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

@Controller
public class ShopController {

	@Autowired
	private ShopService shopService;
	
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
	
	@RequestMapping(method=RequestMethod.GET, value="/api/shops")
	public ResponseEntity<Object> getNearbyShops(final @RequestParam(value="latitude", required = true)double latitude, final @RequestParam(value="longitude", required = true)double longitude) {
		return new ResponseEntity<>(shopService.getNearbyShops(latitude, longitude), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/api/shops/all")
	public ResponseEntity<Object> getAllShops() {
		return new ResponseEntity<>(shopService.getAllShops(), HttpStatus.OK);
	}
	
}
