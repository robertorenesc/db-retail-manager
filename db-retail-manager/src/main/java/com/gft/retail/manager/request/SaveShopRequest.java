package com.gft.retail.manager.request;

/**
 * Class that represent the POJO structure of the save shop REST request
 * 
 * @author Roberto Salazar - GFT
 */
public class SaveShopRequest {
	
	private String shopName;
	
	private String shopAddressNumber;
	
	private String shopAddressPostCode;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddressNumber() {
		return shopAddressNumber;
	}

	public void setShopAddressNumber(String shopAddressNumber) {
		this.shopAddressNumber = shopAddressNumber;
	}

	public String getShopAddressPostCode() {
		return shopAddressPostCode;
	}

	public void setShopAddressPostCode(String shopAddressPostCode) {
		this.shopAddressPostCode = shopAddressPostCode;
	}

}
