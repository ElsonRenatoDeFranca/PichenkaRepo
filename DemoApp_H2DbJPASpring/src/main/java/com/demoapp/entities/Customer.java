
package com.demoapp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="DEMO_CUSTOMERS")
public class Customer {


	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="CUST_ID")
	private Long id;
	
	@Column(name="CUST_PERSONAL_ID")
	private Long personalId;
	
	@Column(name="CUST_FIRST_NAME")
	private String firstName;
	
	@Column(name="CUST_LAST_NAME")
	private String lastName;
		
	@Column(name="CUST_EMAIL")
	private String customerEmail;
	
	@Column(name="CUST_PHONE")
	private String telephoneNumber;
	
	@Embedded
	private Address address;
	
	private List<Order> orders;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the personalId
	 */
	public Long getPersonalId() {
		return personalId;
	}

	/**
	 * @param personalId the personalId to set
	 */
	public void setPersonalId(Long personalId) {
		this.personalId = personalId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * @return the telephoneNumber
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @param telephoneNumber the telephoneNumber to set
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the orders
	 */
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="customer")
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
		
	
}
