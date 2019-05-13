package com.sp.smartreads.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account {
	
	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private long accountId;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "customer_id")
	private long customerId;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="account")
	private Set<GasReading> gasReading;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="account")
	private Set<ElectricityReading> electricityReading;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Set<GasReading> getGasReading() {
		return gasReading;
	}

	public void setGasReading(Set<GasReading> gasReading) {
		this.gasReading = gasReading;
	}

	public Set<ElectricityReading> getElectricityReading() {
		return electricityReading;
	}

	public void setElectricityReading(Set<ElectricityReading> electricityReading) {
		this.electricityReading = electricityReading;
	}
	
}
