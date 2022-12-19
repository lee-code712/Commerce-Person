package com.digital.v3.sql.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.digital.v3.sql.vo.PhoneVO;

@Mapper
public interface PhoneMapper {

	// phone 레코드 생성
	public void createPhone(PhoneVO phoneVo);
	
	// phoneNumber로 phone 조회
	public PhoneVO getPhoneByNumber(String phoneNumber);
	
}
