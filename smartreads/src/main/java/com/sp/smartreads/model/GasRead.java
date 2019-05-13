package com.sp.smartreads.model;

import java.util.Date;

public class GasRead {
	
	private String gasMeterId;
	private float gasReading;
	private Date gasReadingDate;
	public GasRead(String gasMeterId, float gasReading, Date gasReadingDate) {
		super();
		this.gasMeterId = gasMeterId;
		this.gasReading = gasReading;
		this.gasReadingDate = gasReadingDate;
	}
	public GasRead() {
	}
	public String getGasMeterId() {
		return gasMeterId;
	}
	public float getGasReading() {
		return gasReading;
	}
	public Date getGasReadingDate() {
		return gasReadingDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((gasMeterId == null) ? 0 : gasMeterId.hashCode());
		result = prime * result + Float.floatToIntBits(gasReading);
		result = prime * result
				+ ((gasReadingDate == null) ? 0 : gasReadingDate.hashCode());
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
		GasRead other = (GasRead) obj;
		if (gasMeterId == null) {
			if (other.gasMeterId != null)
				return false;
		} else if (!gasMeterId.equals(other.gasMeterId))
			return false;
		if (Float.floatToIntBits(gasReading) != Float
				.floatToIntBits(other.gasReading))
			return false;
		if (gasReadingDate == null) {
			if (other.gasReadingDate != null)
				return false;
		} else if (!gasReadingDate.equals(other.gasReadingDate))
			return false;
		return true;
	}
	
	
	
}