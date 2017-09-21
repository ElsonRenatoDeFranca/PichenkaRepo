package com.demoapp.dao;

import java.util.List;

import com.demoapp.entities.Customer;

/**
 * 
 * 
 *
 */
public interface CustomerInventoryDAO {
	
	/**
	 * Select all customers available at database
	 * @return
	 * @throws Exception
	 */
	public List<Customer> searchAll()throws Exception;
	
	/**
	 * Select a customer by Id
	 * @param personalId
	 * @return Customer
	 * @throws Exception
	 */
	public Customer searchCustomerById(Long personalId)throws Exception;
	
	/**
	 * Select a customer by first name
	 * @return Customer
	 * @throws Exception
	 */
	public Customer searchCustomerByFirstName(String firstName)throws Exception;
	
	/**
	 * Select a customer by last name
	 * @return Customer
	 * @throws Exception
	 */
	public Customer searchCustomerByLastName(String lastName)throws Exception;
	
	
	/**
	 * Update customer by Id
	 * @param customerId
	 * @throws Exception
	 */
	public void updateCustomer(Customer customerDetailsVO)throws Exception;
	
	/**
	 * Delete customer by Id
	 * @param customerId
	 * @throws Exception
	 */
	public void deleteCustomer(Customer customer)throws Exception;
	
	
	/**
	 * Add a new customer to database
	 * 
	 * @param customer
	 * @throws Exception
	 */
	public void addCustomer(Customer customer) throws Exception;


}
