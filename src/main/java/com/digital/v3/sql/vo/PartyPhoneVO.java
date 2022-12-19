package com.digital.v3.sql.vo;

import lombok.Data;

@Data
public class PartyPhoneVO {

	private long personId;
	private long phoneId;
	
	public long getPersonId() {
		long personId = this.personId;
		return personId;
	}
	
	public long getPhoneId() {
		long phoneId = this.phoneId;
		return phoneId;
	}
	
}
