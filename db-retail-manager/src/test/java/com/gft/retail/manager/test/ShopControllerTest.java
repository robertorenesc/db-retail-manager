package com.gft.retail.manager.test;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShopControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getAllShopsTest() {
		@SuppressWarnings("rawtypes")
		List body = this.restTemplate.getForObject("/api/shops/all", List.class);
		Assertions.assertThat(body.size()).isEqualTo(3);
	}
	
}
