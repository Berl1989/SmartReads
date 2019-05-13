package com.sp.smartreads.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ELECTRICITY_READING")
public class ElectricityReading {
	
	@Id
	@Column(name = "electricity_reading_id")
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private long electricityReadingId;
	
	@JoinColumn(name="account_id")
	@ManyToOne()
	private Account account;
	
	@Column(name = "meter_id")
	private String meterId;
	
	@Column(name = "reading_value")
	private float readingValue;
	
	@Column(name = "reading_date")
	private Date readingDate;
	
	@Column(name = "is_latest_reading", nullable=true)
	private boolean isLatestReading;
	
	@Column(name = "reading_number")	
	private long readingNumber;

	public long getElectricityReadingId() {
		return electricityReadingId;
	}

	public void setElectricityReadingId(long electricityReadingId) {
		this.electricityReadingId = electricityReadingId;
	}

	public float getReadingValue() {
		return readingValue;
	}

	public void setReadingValue(float readingValue) {
		this.readingValue = readingValue;
	}

	public Date getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(Date readingDate) {
		this.readingDate = readingDate;
	}

	public boolean isLatestReading() {
		return isLatestReading;
	}

	public void setLatestReading(boolean isLatestReading) {
		this.isLatestReading = isLatestReading;
	}

	public long getReadingNumber() {
		return readingNumber;
	}

	public void setReadingNumber(long readingNumber) {
		this.readingNumber = readingNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getMeterId() {
		return meterId;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}
	
	
	
	
}
