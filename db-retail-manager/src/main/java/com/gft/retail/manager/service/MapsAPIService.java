package com.gft.retail.manager.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gft.retail.manager.model.Address;
import com.gft.retail.manager.model.Shop;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

/**
 * Class that handle all the integration with Google Maps API
 * 
 * @author Roberto Salazar - GFT
 *
 */
@Service
public class MapsAPIService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyBQMh0UjqiPR_XAbpcuJjIPkjY0QsxXj5g");
	
	/**
	 * Assign the coordinates of an Address
	 * 
	 * @param address Address with the info required (number and postal code)
	 */
	public void setCoordinates(Address address) {
		try {
			GeocodingResult[] results =  GeocodingApi.geocode(context, address.toString()).await();
			address.setLatitude(results[0].geometry.location.lat);
			address.setLongitude(results[0].geometry.location.lng);
		} catch (ApiException | InterruptedException | IOException e) {
			address.setLatitude(0);
			address.setLongitude(0);
			logger.error(" ---> Error getting coordinates for: " + address.toString(), e);
		}
	}

	/**
	 * Returns the nearby shop to the X,Y coordinates sent based in a list of address
	 * 
	 * @param latitude Latitude of the sender
	 * @param longitude Longitude of the sender
	 * @param shopList List of places to be compared
	 * @return The nearby Shop of the list sent
	 */
	public Shop getNearbyShop(double latitude, double longitude, List<Shop> shopList) {
		// Calculate distance for each address
		shopList.stream().forEach(s -> {
			s.setDistance(calculateDistance(latitude, longitude, s.getAddress().get(0)));
		});
		// Get the nearby place
		Shop nearbyShop = shopList.stream().filter(s -> s.getDistance() >= 0).min((s1, s2) -> Long.compare(s1.getDistance(), s2.getDistance())).get();
		return nearbyShop;
	}
	
	/**
	 * Calculate the distance between the X,Y coordinates sent and an specific address
	 * 
	 * @param latitude Latitude of the sender
	 * @param longitude Longitude of the sender
	 * @param address Address to be compared
	 * @return A distance in meters to the specific Address
	 */
	private long calculateDistance(double latitude, double longitude, Address address) {
		LatLng origin = new LatLng(latitude, longitude);
		LatLng destination = new LatLng(address.getLatitude(), address.getLongitude());
		try {
			DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context).origins(origin).destinations(destination).await();
			return distanceMatrix.rows[0].elements[0].distance.inMeters;
		} catch (ApiException | InterruptedException | IOException e) {
			logger.error(" ---> Error getting distance for: " + address.toString(), e);
			return -1;
		}
	}
	
}
