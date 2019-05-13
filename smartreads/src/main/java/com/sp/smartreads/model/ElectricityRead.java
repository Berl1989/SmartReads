package com.sp.smartreads.model;

import java.util.Date;

public class ElectricityRead {
	
	private String electricityMeterId;
	private float electricityReading;
	private Date electricityReadingDate;
	public ElectricityRead(String electricityMeterId,
			float electricityReading, Date electricityReadingDate) {
		super();
		this.electricityMeterId = electricityMeterId;
		this.electricityReading = electricityReading;
		this.electricityReadingDate = electricityReadingDate;
	}
	public ElectricityRead() {
		
	}
	public String getElectricityMeterId() {
		return electricityMeterId;
	}
	public float getElectricityReading() {
		return electricityReading;
	}
	public Date getElectricityReadingDate() {
		return electricityReadingDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((electricityMeterId == null) ? 0 : electricityMeterId
						.hashCode());
		result = prime * result + Float.floatToIntBits(electricityReading);
		result = prime
				* result
				+ ((electricityReadingDate == null) ? 0
						: electricityReadingDate.hashCode());
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
		ElectricityRead other = (ElectricityRead) obj;
		if (electricityMeterId == null) {
			if (other.electricityMeterId != null)
				return false;
		} else if (!electricityMeterId.equals(other.electricityMeterId))
			return false;
		if (Float.floatToIntBits(electricityReading) != Float
				.floatToIntBits(other.electricityReading))
			return false;
		if (electricityReadingDate == null) {
			if (other.electricityReadingDate != null)
				return false;
		} else if (!electricityReadingDate.equals(other.electricityReadingDate))
			return false;
		return true;
	}
}