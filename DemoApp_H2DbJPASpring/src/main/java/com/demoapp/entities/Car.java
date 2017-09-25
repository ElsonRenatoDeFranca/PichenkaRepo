/**
 * 
 */
package com.demoapp.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author userpc
 *
 */
@Entity
public class Car {

	/*
	 * 
	 */
	@EmbeddedId
	private CarId cardId;
	
	/**
	 * 
	 */
	private String name;

	/**
	 * @return the cardId
	 */
	public CarId getCardId() {
		return cardId;
	}

	/**
	 * @param cardId the cardId to set
	 */
	public void setCardId(CarId cardId) {
		this.cardId = cardId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
