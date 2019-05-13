package com.sp.smartreads.model;

import java.io.Serializable;

import com.sp.smartreads.entity.ElectricityReading;
import com.sp.smartreads.entity.GasReading;


public class AccountReadingDto implements Serializable{
	
	private static final long serialVersionUID = -3089748700595767447L;

	private String accountNumber;
	
	private GasRead gasRead;
	
	private ElectricityRead electricityRead;	

	public AccountReadingDto(String accountNumber, GasReading gasReading, ElectricityReading electricityReading) {
		super();
		this.accountNumber = accountNumber;		
		if (gasReading != null) {
			gasRead = new GasRead(gasReading.getMeterId(),gasReading.getReadingValue(), gasReading.getReadingDate());
		}
		if (electricityReading != null) {
			electricityRead = new ElectricityRead(electricityReading.getMeterId(), electricityReading.getReadingValue(), electricityReading.getReadingDate());
		}		
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public GasRead getGasRead() {
		return gasRead;
	}

	public ElectricityRead getElectricityRead() {
		return electricityRead;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result
				+ ((electricityRead == null) ? 0 : electricityRead.hashCode());
		result = prime * result + ((gasRead == null) ? 0 : gasRead.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountReadingDto other = (AccountReadingDto) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (electricityRead == null) {
			if (other.electricityRead != null)
				return false;
		} else if (!electricityRead.equals(other.electricityRead))
			return false;
		if (gasRead == null) {
			if (other.gasRead != null)
				return false;
		} else if (!gasRead.equals(other.gasRead))
			return false;
		return true;
	}
	
}