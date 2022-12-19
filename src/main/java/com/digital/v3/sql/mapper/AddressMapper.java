package com.digital.v3.sql.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.digital.v3.sql.vo.AddressVO;

@Mapper
public interface AddressMapper {

	// address 레코드 생성
	public void createAddress(AddressVO addressVo);
	
	// addressDetail로 address 조회
	public AddressVO getAddressByDetail(String addressDetail);
	
}
