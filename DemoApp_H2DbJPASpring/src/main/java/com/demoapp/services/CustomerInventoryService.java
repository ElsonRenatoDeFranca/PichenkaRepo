package com.demoapp.services;

import java.util.List;

import com.demoapp.util.dto.CustomerDetailsDTO;
import com.demoapp.util.vo.CustomerDetailsVO;

/**
 * 
 * 
 *
 */
public interface CustomerInventoryService {

	/**
	 * Select all customers available at database
	 * @return
	 * @throws Exception
	 */
	public List<CustomerDetailsDTO> searchAll()throws Exception;
	
	/**
	 * Select a customer by Id
	 * @param id
	 * @return Long
	 * @throws Exception
	 */
	public CustomerDetailsDTO searchCustomerById(Long personalId)throws Exception;
	
	/**
	 * Select a customer by first name
	 * @return CustomerDetailsDTO
	 * @throws Exception
	 */
	public CustomerDetailsDTO searchCustomerByFirstName(String firstName)throws Exception;
	
	/**
	 * Select a customer by last name
	 * @return CustomerDetailsDTO
	 * @throws Exception
	 */
	public CustomerDetailsDTO searchCustomerByLastName(String lastName)throws Exception;
	
	
	/**
	 * Update customer by Id
	 * @param customerId
	 * @throws Exception
	 */
	public void updateCustomer(CustomerDetailsVO customerDetailsVO)throws Exception;
	
	/**
	 * Delete customer by Id
	 * @param customerId
	 * @throws Exception
	 */
	public void deleteCustomer(CustomerDetailsVO customerDetailsVO)throws Exception;
	
	
	/**
	 * Add a new customer to database
	 * 
	 * @param customerDetailsVO
	 * @throws Exception
	 */
	public void addCustomer(CustomerDetailsVO customerDetailsVO) throws Exception;

}
