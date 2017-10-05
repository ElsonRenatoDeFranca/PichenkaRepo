/**
 * 
 */
package com.demoapp.entities;

import javax.persistence.Embeddable;

/**
 * @author userpc
 *
 */
@Embeddable
public class Address {

	/**
	 * 
	 */
	private String street;
	/**
	 * 
	 */
	private String zipCode;
	
	/**
	 * 
	 */
	private String city;
	
	/**
	 * 
	 */
	private String country;

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
}
