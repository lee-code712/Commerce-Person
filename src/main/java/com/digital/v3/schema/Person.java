package com.digital.v3.schema;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Person {
//	personId (long)
//	personName (String)
//	gender (String)
//	phoneId A(long)
//	addressId A(long)

	@ApiModelProperty(required = false, position = 1, notes = "고객 ID", example = "0", dataType = "long")
	private long personId;
	
	@ApiModelProperty(required = true, position = 2, notes = "고객명", example = "고객명", dataType = "string")
	private String personName;
	
	@ApiModelProperty(required = true, position = 3, notes = "패스워드", example = "패스워드", dataType = "string")
	private String password;
	
	@ApiModelProperty(required = true, position = 4, notes = "고객 성별", example = "F/M", dataType = "string")
	private String gender;
	
	@ApiModelProperty(required = true, position = 5, notes = "고객 전화번호 목록", example = "", dataType = "array")
	private List<Phone> phoneList;
	
	@ApiModelProperty(required = true, position = 6, notes = "고객 주소 목록", example = "", dataType = "array")
	private List<Address> addressList;

	public long getPersonId() {
		long personId = this.personId;
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		String personName = this.personName;
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	public String getPassword() {
		String password = this.password;
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		String gender = this.gender;
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Phone> getPhoneList() {
		List<Phone> phoneList = this.phoneList;
		return phoneList;
	}

	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}

	public List<Address> getAddressList() {
		List<Address> addressList = this.addressList;
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

}
