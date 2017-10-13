/**
 * 
 */
package com.demoapp.converter;

import java.util.ArrayList;
import java.util.List;

import com.demoapp.entities.Customer;
import com.demoapp.util.dto.CustomerDetailsDTO;
import com.demoapp.util.vo.CustomerDetailsVO;

/**
 * @author userpc
 *
 */
public class CustomerInventoryConverter {

	/**
	 * 
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public static CustomerDetailsDTO converEntityObjecttToDTOObject(Customer customer){
		
		CustomerDetailsDTO customerDetailsDTO = null;
		
		if(customer != null){
			customerDetailsDTO = new CustomerDetailsDTO();
			customerDetailsDTO.setEmail(customer.getCustomerEmail());
			customerDetailsDTO.setFirstName(customer.getFirstName());
			customerDetailsDTO.setLastName(customer.getLastName());
			customerDetailsDTO.setPersonalId(customer.getPersonalId());
			customerDetailsDTO.setTelephone(customer.getTelephoneNumber());
			customerDetailsDTO.setOrders(customer.getOrders());
		}
		return customerDetailsDTO;
	}
	
	/**
	 * 
	 * @param customerList
	 * @return
	 * @throws Exception
	 */
	public static List<CustomerDetailsDTO> convertEntityListToDTOList(List<Customer> customerList)throws Exception{
		
		List<CustomerDetailsDTO> customerDetailsDTOList = null;
    	
		if(customerList != null){
			customerDetailsDTOList = new ArrayList<CustomerDetailsDTO>();
	    	for(Customer customer: customerList){
	    		CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
	    		customerDetailsDTO.setPersonalId(customer.getPersonalId());
	    		customerDetailsDTO.setEmail(customer.getCustomerEmail());
	    		customerDetailsDTO.setFirstName(customer.getFirstName());
				customerDetailsDTO.setLastName(customer.getLastName());
				customerDetailsDTO.setTelephone(customer.getTelephoneNumber());
				customerDetailsDTO.setOrders(customer.getOrders());
				customerDetailsDTOList.add(customerDetailsDTO);
	    	}
	    }
		
		return customerDetailsDTOList;
		
	}
	
	/**
	 * 
	 * @param customerDetailsVO
	 * @return
	 * @throws Exception
	 */
	public static Customer convertVOObjecttoEntityObject(CustomerDetailsVO customerDetailsVO)throws Exception{
		
		Customer customer = null;
		
		if(customerDetailsVO != null){
			customer = new Customer();
			customer.setCustomerEmail(customerDetailsVO.getEmail());
			customer.setFirstName(customerDetailsVO.getFirstName());
			customer.setLastName(customerDetailsVO.getLastName());
			customer.setPersonalId(customerDetailsVO.getPersonalId());
			customer.setTelephoneNumber(customerDetailsVO.getTelephone());
			//customer.setOrders(customerDetailsVO.getOrders());
		}
		
		return customer;
	}
	
}
