package com.digital.v3.schema;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@ArraySchema
public class Phone {
//	phoneId (long)
//	phoneNumber (String)

	@ApiModelProperty(required = false, position = 1, notes = "전화번호 ID", example = "0", dataType = "long")
	private long phoneId;
	
	@ApiModelProperty(required = true, position = 2, notes = "전화번호 상세", example = "xxx-xxxx-xxxx", dataType = "string")
	private String phoneNumber;
	
	public long getPhoneId() {
		long phoneId = this.phoneId;
		return phoneId;
	}
	
	public void setPhoneId(long phoneId) {
		this.phoneId = phoneId;
	}
	
	public String getPhoneNumber() {
		String phoneNumber = this.phoneNumber; 
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
