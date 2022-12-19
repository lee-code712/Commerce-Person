package com.digital.v3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.v3.schema.Phone;
import com.digital.v3.sql.mapper.PhoneMapper;
import com.digital.v3.sql.vo.PhoneVO;

@Component
public class PhoneService {
	
	@Autowired
	PhoneMapper phoneMapper;
	
	/* 전화번호 등록 */
	public boolean phoneWrite (Phone phone) throws Exception {
		try {
			// phone 중복 여부 확인
			if (phoneMapper.getPhoneByNumber(phone.getPhoneNumber()) != null) {
				throw new Exception("이미 등록된 전화번호입니다."); 
			}
			
			// 중복이 아니면 write
			phone.setPhoneId(System.currentTimeMillis());
			PhoneVO phoneVo = setPhoneVO(phone);
			
			phoneMapper.createPhone(phoneVo);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* 전화번호 검색 - phoneNumber */
	public Phone phoneSearch (String phoneNumber) throws Exception {

		PhoneVO phoneVo = phoneMapper.getPhoneByNumber(phoneNumber);
		
		Phone phone = new Phone();
		if (phoneVo != null) {
			phone = setPhone(phoneVo);
		}
		
		return phone;
	}
	
	public Phone setPhone (PhoneVO phoneVo) {
		Phone phone = new Phone();
		
		phone.setPhoneId(phoneVo.getPhoneId());
		phone.setPhoneNumber(phoneVo.getPhoneNumber());
		
		return phone;
	}
	
	public PhoneVO setPhoneVO (Phone phone) {
		PhoneVO phoneVo = new PhoneVO();
		
		phoneVo.setPhoneId(phone.getPhoneId());
		phoneVo.setPhoneNumber(phone.getPhoneNumber());
		
		return phoneVo;
	}
	
}
