package com.digital.v3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.digital.v3.schema.Address;
import com.digital.v3.schema.Person;
import com.digital.v3.schema.Phone;
import com.digital.v3.sql.mapper.PersonMapper;
import com.digital.v3.sql.vo.AddressVO;
import com.digital.v3.sql.vo.PartyAddressVO;
import com.digital.v3.sql.vo.PartyPhoneVO;
import com.digital.v3.sql.vo.PersonVO;
import com.digital.v3.sql.vo.PhoneVO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Component
public class PersonService {
	
	@Resource
	AddressService addressSvc;
	@Resource
	PhoneService phoneSvc;
	@Autowired
	PersonMapper personMapper;

	/* 회원 등록 */
	@Transactional
	public boolean signUp (Person person) throws Exception {
		try {
			// person 중복 여부 확인
			if (personMapper.getPersonByName(person.getPersonName()) != null) {
				throw new Exception("이미 가입한 회원입니다.");
			}

			// 중복이 아니면 person write
			person.setPersonId(System.currentTimeMillis());
			PersonVO personVo = setPersonVO(person);

			personMapper.createPerson(personVo);
			
			// address & party address write
			List<Address> addressList = person.getAddressList();
			for (Address address : addressList) {
				try {
					if (addressSvc.addressWrite(address)) {
						partyAddressWrite(personVo.getPersonId(), addressSvc.addressSearch(address.getAddressDetail()).getAddressId());
					}
				} catch (Exception e) {
					partyAddressWrite(personVo.getPersonId(), addressSvc.addressSearch(address.getAddressDetail()).getAddressId());
				}
			}
			
			// phone & party phone write
			List<Phone> phoneList = person.getPhoneList();
			for (Phone phone : phoneList) {
				try {
					if (phoneSvc.phoneWrite(phone)) {
						partyPhoneWrite(personVo.getPersonId(), phoneSvc.phoneSearch(phone.getPhoneNumber()).getPhoneId());
					}
				} catch (Exception e) {
					partyPhoneWrite(personVo.getPersonId(), phoneSvc.phoneSearch(phone.getPhoneNumber()).getPhoneId());
				}
			}
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* 회원 로그인 정보 확인 */
	public Person login (Person person) throws Exception {
		
		Person findPerson = personSearch(person.getPersonName());
		
		if (findPerson.getPersonName() == null) {
			return null;
		}
		else if (!person.getPassword().equals(findPerson.getPassword())) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		
		return findPerson;
	}
	
	/* 회원 검색 - personName */
	public Person personSearch (String personName) throws Exception {
		
		PersonVO personVo = personMapper.getPersonByName(personName);
		
		Person person = new Person();
		if (personVo != null) {
			person = setPerson(personVo);
		}
		
		return person;
	}
	
	/* 회원 검색 - personId */
	public Person personSearchById (long personId) throws Exception {
		
		PersonVO personVo = personMapper.getPersonById(personId);
		
		Person person = new Person();
		if (personVo != null) {
			person = setPerson(personVo);
		}
		
		return person;
	}
	
	/* 회원 주소 등록 */
	public boolean partyAddressWrite (long personId, long addressId) throws Exception {
		try {
			PartyAddressVO partyAddressVo = setPartyAddressVo(personId, addressId);
			
			// party address 중복 여부 확인
			if (personMapper.isExistPartyAddress(partyAddressVo) > 0) {
				throw new Exception("회원 정보에 이미 등록된 주소입니다."); 
			}

			// 중복이 아니면 write
			personMapper.createPartyAddress(partyAddressVo);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* 회원 주소 삭제 */
	public boolean partyAddressDelete (long personId, long addressId) throws Exception {
		try {
			PartyAddressVO partyAddressVo = setPartyAddressVo(personId, addressId);
			
			// party address 존재 여부 확인
			if (personMapper.isExistPartyAddress(partyAddressVo) == 0) {
				throw new Exception("회원 정보에 해당 주소가 없습니다."); 
			} 
	
			// 존재하면 delete
			personMapper.deletePartyAddress(partyAddressVo);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* 회원 전화번호 등록 */
	public boolean partyPhoneWrite (long personId, long phoneId) throws Exception {
		try {
			PartyPhoneVO partyPhoneVo = setPartyPhoneVo(personId, phoneId);
			
			// party phone 중복 여부 확인
			if (personMapper.isExistPartyPhone(partyPhoneVo) > 0) {
				throw new Exception("회원정보에 이미 등록된 전화번호입니다."); 
			} 
	
			// 중복이 아니면 write
			personMapper.createPartyPhone(partyPhoneVo);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/* 회원 전화번호 삭제 */
	public boolean partyPhoneDelete (long personId, long phoneId) throws Exception {
		try {
			PartyPhoneVO partyPhoneVo = setPartyPhoneVo(personId, phoneId);
			
			// party phone 존재 여부 확인
			if (personMapper.isExistPartyPhone(partyPhoneVo) == 0) {
				throw new Exception("회원정보에 해당 전화번호가 없습니다."); 
			} 
	
			// 존재하면 delete
			personMapper.deletePartyPhone(partyPhoneVo);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Person setPerson (PersonVO personVo) {
		Person person = new Person();
		
		person.setPersonId(personVo.getPersonId());
		person.setPersonName(personVo.getPersonName());
		person.setPassword(personVo.getPassword());
		person.setGender(personVo.getGender());
		
		// address list set
		List<Address> addressList = new ArrayList<Address>();
		for (AddressVO addressVo : personVo.getAddressVoList()) {
			Address address = addressSvc.setAddress(addressVo);
			addressList.add(address);
		}
		person.setAddressList(addressList);
		
		// phone list set
		List<Phone> phoneList = new ArrayList<Phone>();
		for (PhoneVO phoneVo : personVo.getPhoneVoList()) {
			Phone phone = phoneSvc.setPhone(phoneVo);
			phoneList.add(phone);
		}
		person.setPhoneList(phoneList);
		
		return person;
	}
	
	public PersonVO setPersonVO (Person person) {
		PersonVO personVo = new PersonVO();
		
		personVo.setPersonId(person.getPersonId());
		personVo.setPersonName(person.getPersonName());
		personVo.setPassword(person.getPassword());
		personVo.setGender(person.getGender());
		
		return personVo;
	}
	
	public PartyAddressVO setPartyAddressVo (long personId, long addressId) {
		PartyAddressVO partyAddressVo = new PartyAddressVO();
		
		partyAddressVo.setPersonId(personId);
		partyAddressVo.setAddressId(addressId);
		
		return partyAddressVo;
	}
	
	public PartyPhoneVO setPartyPhoneVo (long personId, long phoneId) {
		PartyPhoneVO partyPhoneVo = new PartyPhoneVO();
		
		partyPhoneVo.setPersonId(personId);
		partyPhoneVo.setPhoneId(phoneId);
		
		return partyPhoneVo;
	}
}
