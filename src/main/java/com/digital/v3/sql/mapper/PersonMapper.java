package com.digital.v3.sql.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.digital.v3.sql.vo.PartyAddressVO;
import com.digital.v3.sql.vo.PartyPhoneVO;
import com.digital.v3.sql.vo.PersonVO;

@Mapper
public interface PersonMapper {

	// person 레코드 생성
	public void createPerson(PersonVO personVo);
	
	// personName으로 person 조회
	public PersonVO getPersonByName(String personName);
	
	// personId로 person 조회
	public PersonVO getPersonById(long personId);
	
	// party address 생성
	public void createPartyAddress(PartyAddressVO partyAddressVo);
	
	// personId, addressId로 party address 레코드 개수 조회 (존재여부 확인)
	public int isExistPartyAddress(PartyAddressVO partyAddressVo);
	
	// personId, addressId로 party address 레코드 삭제
	public void deletePartyAddress(PartyAddressVO partyAddressVo);
	
	// party phone 생성
	public void createPartyPhone(PartyPhoneVO partyPhoneVo);
	
	// personId, phoneId로 party phone 레코드 개수 조회 (존재여부 확인)
	public int isExistPartyPhone(PartyPhoneVO partyPhoneVo);
	
	// personId, phoneId로 party phone 레코드 삭제
	public void deletePartyPhone(PartyPhoneVO partyPhoneVo);
	
}
