package com.sp.smartreads.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.smartreads.dao.CustomerAccountDao;
import com.sp.smartreads.model.AccountReadingDto;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService{

	@Autowired
	private CustomerAccountDao customerDao;
	
	@Override
	public AccountReadingDto getLatestAccountReading(String accountNumber) {
		AccountReadingDto accountReading = customerDao.getLatestAccountReading(accountNumber);
		
		if(accountReading == null) {
			throw new EntityNotFoundException("Customer Account Not Found for Account Number " + accountNumber);
		} else {
			return accountReading;
		}
	}
	
}
