package com.sp.smartreads.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sp.smartreads.entity.Customer;
import com.sp.smartreads.model.AccountReadingDto;

@Repository
public interface CustomerAccountDao extends CrudRepository<Customer,Long>{
	
	@Query("select new com.sp.smartreads.model.AccountReadingDto(a.accountNumber,gs, es) from Account a "
			+ "left join a.gasReading gs on gs.isLatestReading = true "
			+ "left join a.electricityReading es on es.isLatestReading = true "
			+ "where a.accountNumber = ?1")
	public AccountReadingDto getLatestAccountReading(String accountNumber);

}
