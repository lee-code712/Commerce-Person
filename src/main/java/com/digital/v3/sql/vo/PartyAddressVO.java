package com.digital.v3.sql.vo;

import lombok.Data;

@Data
public class PartyAddressVO {

	private long personId;
	private long addressId;
	
	public long getPersonId() {
		long personId = this.personId;
		return personId;
	}
	
	public long getAddressId() {
		long addressId = this.addressId;
		return addressId;
	}
	
}
