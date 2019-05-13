package com.sp.smartreads.service;

import com.sp.smartreads.model.AccountReadingDto;

public interface CustomerAccountService {

	public AccountReadingDto getLatestAccountReading(String accountNumber);
	
}
