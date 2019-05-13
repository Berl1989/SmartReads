package com.sp.smartreads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sp.smartreads.model.AccountReadingDto;
import com.sp.smartreads.service.CustomerAccountService;

@RestController
@RequestMapping("/api/smart/reads")
public class SmartReadsController {

	@Autowired
	private CustomerAccountService customerAccountService;
	
	@RequestMapping(value = "/{ACCOUNTNUMBER}", method = RequestMethod.GET)
	public AccountReadingDto getCustomerAccountDetail(@PathVariable(name="ACCOUNTNUMBER") String accountNumber) {
		return customerAccountService.getLatestAccountReading(accountNumber);
	}
	
}
