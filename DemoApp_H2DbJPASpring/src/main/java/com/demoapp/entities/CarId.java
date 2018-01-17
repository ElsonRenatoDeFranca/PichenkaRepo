/**
 * 
 */
package com.demoapp.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author userpc
 *
 */
@Embeddable
public class CarId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8636281819359604196L;

	/**
	 * 
	 */
	private int serial;

	/**
	 * 
	 */
	private String brand;

	/**
	 * 
	 */
	public CarId() {
	}

	/**
	 * 
	 * @param serial
	 * @param brand
	 */
	public CarId(int serial, String brand) {
		this.serial = serial;
		this.brand = brand;
	}

	/**
	 * @return the serial
	 */
	public int getSerial() {
		return serial;
	}

	/**
	 * @param serial the serial to set
	 */
	public void setSerial(int serial) {
		this.serial = serial;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + serial;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarId other = (CarId) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (serial != other.serial)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CarId [serial=" + serial + ", brand=" + brand + "]";
	}
	
	

}
