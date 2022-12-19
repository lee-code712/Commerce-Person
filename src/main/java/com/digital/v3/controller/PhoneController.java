package com.digital.v3.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digital.v3.schema.ErrorMsg;
import com.digital.v3.schema.Phone;
import com.digital.v3.service.PhoneService;
import com.digital.v3.utils.ExceptionUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "전화번호", description = "Phone Related API")
@RequestMapping(value = "/rest/phone")
public class PhoneController {

	@Resource
	private PhoneService phoneSvc;
	
	@RequestMapping(value = "/write", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "전화번호 등록", notes = "전화번호를 등록하기 위한 API. *입력 필드: phoneNumber")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = Phone.class),
		@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> phoneWrite (@ApiParam(value = "전화번호 정보", required = true) @RequestBody Phone phone) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		
		Phone resPhone = new Phone();
		try {
			if (phoneSvc.phoneWrite(phone)) {
				resPhone = phoneSvc.phoneSearch(phone.getPhoneNumber());
			}
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
		return new ResponseEntity<Phone>(resPhone, header, HttpStatus.valueOf(200));
	}

	@RequestMapping(value = "/inquiry/{phoneNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "전화번호 검색", notes = "전화번호 상세로 전화번호 정보를 검색하기 위한 API.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = Phone.class),
		@ApiResponse(code = 500, message = "실패", response = ErrorMsg.class)
	})
	public ResponseEntity<?> phoneSearch (@ApiParam(value = "전화번호 상세", required = true) @PathVariable String phoneNumber) {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		ErrorMsg errors = new ErrorMsg();
		
		try {
			Phone phone = phoneSvc.phoneSearch(phoneNumber);
			return new ResponseEntity<Phone>(phone, header, HttpStatus.valueOf(200));
		} catch (Exception e) {
			return ExceptionUtils.setException(errors, 500, e.getMessage(), header);
		}
	}
}
