package com.digital.v3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.v3.schema.Address;
import com.digital.v3.sql.mapper.AddressMapper;
import com.digital.v3.sql.vo.AddressVO;

@Component
public class AddressService {
	
	@Autowired
	AddressMapper addressMapper;

	/* 주소 등록 */
	public boolean addressWrite (Address address) throws Exception {
		try {
			// address 중복 여부 확인
			if (addressMapper.getAddressByDetail(address.getAddressDetail()) != null) {
				throw new Exception("이미 등록된 주소입니다."); 
			} 
	
			// 중복이 아니면 write
			address.setAddressId(System.currentTimeMillis());
			AddressVO addressVo = setAddressVO(address);
			
			addressMapper.createAddress(addressVo);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* 주소 검색 - addressDetail */
	public Address addressSearch (String addressDetail) throws Exception {
		
		AddressVO addressVo = addressMapper.getAddressByDetail(addressDetail);
		
		Address address = new Address();
		if (addressVo != null) {
			address = setAddress(addressVo);
		}
		
		return address;
	}
	
	public Address setAddress (AddressVO addressVo) {
		Address address = new Address();
		
		address.setAddressId(addressVo.getAddressId());
		address.setAddressDetail(addressVo.getAddressDetail());
		
		return address;
	}
	
	public AddressVO setAddressVO (Address address) {
		AddressVO addressVo = new AddressVO();
		
		addressVo.setAddressId(address.getAddressId());
		addressVo.setAddressDetail(address.getAddressDetail());
		
		return addressVo;
	}
}
