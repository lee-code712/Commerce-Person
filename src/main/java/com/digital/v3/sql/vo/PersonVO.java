package com.digital.v3.sql.vo;

import java.util.List;

import lombok.Data;

@Data
public class PersonVO {

	private long personId;
	private String personName;
	private String gender;
	private String password;
	private List<AddressVO> addressVoList;
	private List<PhoneVO> phoneVoList;
	
	public long getPersonId() {
		long personId = this.personId;
		return personId;
	}
	
	public String getPersonName() {
		String personName = this.personName;
		return personName;
	}
	
	public String getGender() {
		String gender = this.gender;
		return gender;
	}

	public String getPassword() {
		String password = this.password; 
		return password;
	}

	public List<AddressVO> getAddressVoList() {
		List<AddressVO> addressVoList = this.addressVoList;
		return addressVoList;
	}

	public List<PhoneVO> getPhoneVoList() {
		List<PhoneVO> phoneVoList = this.phoneVoList;
		return phoneVoList;
	}
	
}
