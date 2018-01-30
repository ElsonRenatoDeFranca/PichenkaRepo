package com.demoapp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.demoapp.dao.CustomerInventoryDAO;
import com.demoapp.services.CustomerInventoryService;
import com.demoapp.util.to.CustomerDetailsDTO;
import com.demoapp.util.vo.CustomerDetailsVO;

/**
 * 
 * 
 *
 */

@Service("customerInventoryService")
@Scope("singleton")
public class CustomerInventoryServiceImpl implements CustomerInventoryService{

	/**
	 * 
	 */
	@Autowired
	private CustomerInventoryDAO customerInventoryDAO;
	

	@Override
	public void addCustomer(CustomerDetailsVO customerDetailsVO) throws Exception {
		
		if(customerDetailsVO != null){
			
			System.out.println("CustomerVO: "+customerDetailsVO.toString());
			CustomerDetailsDTO customerDetailsDTO = customerInventoryDAO.searchCustomerById(customerDetailsVO.getCustomerId());
			
			if(customerDetailsDTO == null){
				this.customerInventoryDAO.addCustomer(customerDetailsVO);
			}else{
				System.out.println("Customer already exists in the database");
			}
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.demoapp.integrationtest.h2.services.CustomerService#getAllCustomers()
	 */
	@Override
	public List<CustomerDetailsDTO> searchAll() throws Exception {
		List<CustomerDetailsDTO> customers = customerInventoryDAO.searchAll();
		return customers;
	}

	/* (non-Javadoc)
	 * @see com.demoapp.integrationtest.h2.services.CustomerService#getCustomerById()
	 */
	@Override
	public CustomerDetailsDTO searchCustomerById(CustomerDetailsVO customerDetailsVO) throws Exception {
		
		CustomerDetailsDTO customerDetailsDTO = null;
		
		if(customerDetailsVO != null){
			customerDetailsDTO = customerInventoryDAO.searchCustomerById(customerDetailsVO.getCustomerId());
			
		}
		
		return customerDetailsDTO;
	}

	/* (non-Javadoc)
	 * @see com.demoapp.integrationtest.h2.services.CustomerService#searchCustomerByFirstName()
	 */
	@Override
	public CustomerDetailsDTO searchCustomerByFirstName(CustomerDetailsVO customerDetailsVO) throws Exception {
		
		CustomerDetailsDTO customerDetailsDTO = null;
		
		if(customerDetailsVO != null){
			customerDetailsDTO = customerInventoryDAO.searchCustomerByFirstName(customerDetailsVO.getFirstName());
		}
		
		return customerDetailsDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.demoapp.integrationtest.h2.services.CustomerService#searchCustomerByLastName()
	 */
	@Override
	public CustomerDetailsDTO searchCustomerByLastName(CustomerDetailsVO customerDetailsVO) throws Exception {
		
		CustomerDetailsDTO customerDetailsDTO = null;
		
		if(customerDetailsVO != null){
			customerDetailsDTO = customerInventoryDAO.searchCustomerByLastName(customerDetailsVO.getLastName());
		}
		
		return customerDetailsDTO;
	}


	/* (non-Javadoc)
	 * @see com.demoapp.integrationtest.h2.services.CustomerService#updateCustomerById(java.lang.String)
	 */
	@Override
	public void updateCustomerById(CustomerDetailsVO customerDetailsVO) throws Exception {
		customerInventoryDAO.updateCustomerById(customerDetailsVO);
	}

	/* (non-Javadoc)
	 * @see com.demoapp.integrationtest.h2.services.CustomerService#deleteCustomerById(java.lang.String)
	 */
	@Override
	public void deleteCustomerById(CustomerDetailsVO customerDetailsVO) throws Exception {
		
		CustomerDetailsDTO customerDetailsDTO = null;
		
		if(customerDetailsVO != null){
			
			customerDetailsDTO = customerInventoryDAO.searchCustomerById(customerDetailsVO.getCustomerId());
			
			if(customerDetailsDTO != null){
				this.customerInventoryDAO.deleteCustomerById(customerDetailsVO.getCustomerId());
			}else{
				System.out.println("Customer doesn't exist in the database");
			}
		}
		

	}

	/**
	 * @return the customerInventoryDAO
	 */
	public CustomerInventoryDAO getCustomerInventoryDAO() {
		return customerInventoryDAO;
	}

	/**
	 * @param customerInventoryDAO the customerInventoryDAO to set
	 */
	public void setCustomerInventoryDAO(CustomerInventoryDAO customerInventoryDAO) {
		this.customerInventoryDAO = customerInventoryDAO;
	}

	



}
