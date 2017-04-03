package com.gft.retail.manager.test;

import java.net.URI;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.gft.retail.manager.model.Shop;
import com.gft.retail.manager.request.SaveShopRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShopControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getAllShopsTest() {
		@SuppressWarnings("rawtypes")
		List body = this.restTemplate.getForObject("/api/shops/all", List.class);
		Assertions.assertThat(body.size()).isEqualTo(4);
	}
	
	@Test 
	public void saveShopTest() {
		SaveShopRequest request = new SaveShopRequest();
		request.setShopName("Columbia Road Shop");
		request.setShopAddressNumber("Columbia Rd");
		request.setShopAddressPostCode("E2 7RG");
		Shop body = this.restTemplate.postForObject("/api/shops", request, Shop.class);
		Assertions.assertThat(body.getAddress().size()).isGreaterThan(0);
	}
	
	@Test
	public void getNearbyShopsTest() {
		URI targetURL = UriComponentsBuilder.fromUriString("/")
			    .path("api/shops")
			    .queryParam("latitude", "51.497277")
			    .queryParam("longitude", "-0.179377")
			    .build()
			    .toUri();
		Shop body = this.restTemplate.getForObject(targetURL ,Shop.class);
		Assertions.assertThat(body.getName()).isEqualTo("Hyde Park Shop");
	}
	
}
