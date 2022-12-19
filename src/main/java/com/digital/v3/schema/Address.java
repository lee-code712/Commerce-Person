package com.digital.v3.schema;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@ArraySchema
public class Address {
//	addressId (long)
//	addressDetail (String)
	
	@ApiModelProperty(required = false, position = 1, notes = "주소 ID", example = "0", dataType = "long")
	private long addressId;
	
	@ApiModelProperty(required = true, position = 2, notes = "주소 상세", example = "xx시 xx구", dataType = "string")
	private String addressDetail;

	public long getAddressId() {
		long addressId = this.addressId;
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getAddressDetail() {
		String addressDetail = this.addressDetail;
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

}
