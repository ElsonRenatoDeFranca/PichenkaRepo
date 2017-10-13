package com.demoapp.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoapp.converter.CustomerInventoryConverter;
import com.demoapp.dao.CustomerInventoryDAO;
import com.demoapp.entities.Customer;
import com.demoapp.exception.CustomerFieldException;
import com.demoapp.exception.CustomerFieldExceptionMessage;
import com.demoapp.services.CustomerInventoryService;
import com.demoapp.util.dto.CustomerDetailsDTO;
import com.demoapp.util.vo.CustomerDetailsVO;

/**
 * 
 * 
 *
 */

@Service("customerInventoryService")
public class CustomerInventoryServiceImpl implements CustomerInventoryService {
	/**
	 * 
	 */
	@Autowired
	private CustomerInventoryDAO customerInventoryDAO;


	private static final Logger LOG = LogManager.getLogger("Log");
	
	@Override
	public void addCustomer(CustomerDetailsVO customerDetailsVO) throws Exception {
		
	LOG.debug(CustomerInventoryServiceImpl.class+",");

		if (customerDetailsVO != null) {
			
			Customer existingCustomer = customerInventoryDAO.searchCustomerById(customerDetailsVO.getPersonalId());

			if (existingCustomer == null || existingCustomer.getPersonalId() == null) {
				Customer customer = CustomerInventoryConverter.convertVOObjecttoEntityObject(customerDetailsVO);
				this.customerInventoryDAO.addCustomer(customer);
			} else {
				throw new CustomerFieldException(CustomerFieldExceptionMessage.CUSTOMER_ALREADY_EXISTS_CODE,
						CustomerFieldExceptionMessage.CUSTOMER_ALREADY_EXISTS_MESSAGE);
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demoapp.integrationtest.h2.services.CustomerService#getAllCustomers()
	 */
	@Override
	public List<CustomerDetailsDTO> searchAll() throws Exception {
		List<CustomerDetailsDTO> customers = CustomerInventoryConverter
				.convertEntityListToDTOList(customerInventoryDAO.searchAll());
		return customers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demoapp.integrationtest.h2.services.CustomerService#getCustomerById()
	 */
	@Override
	public CustomerDetailsDTO searchCustomerById(Long personalId) throws Exception {

		CustomerDetailsDTO customerDetailsDTO = null;

		if (personalId != null) {
			Customer customer = customerInventoryDAO.searchCustomerById(personalId);
			customerDetailsDTO = CustomerInventoryConverter.converEntityObjecttToDTOObject(customer);
		} else {
			throw new CustomerFieldException(CustomerFieldExceptionMessage.CUSTOMER_ID_NOT_FOUND_CODE,
					CustomerFieldExceptionMessage.CUSTOMER_ID_NOT_FOUND_MESSAGE);
		}

		return customerDetailsDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demoapp.integrationtest.h2.services.CustomerService#
	 * searchCustomerByFirstName()
	 */
	@Override
	public CustomerDetailsDTO searchCustomerByFirstName(String firstName) throws Exception {

		CustomerDetailsDTO customerDetailsDTO = null;
		
		if(firstName != null){
			Customer customer = customerInventoryDAO.searchCustomerByFirstName(firstName);
			customerDetailsDTO = CustomerInventoryConverter.converEntityObjecttToDTOObject(customer);

		}else{
			throw new CustomerFieldException(CustomerFieldExceptionMessage.FIRST_NAME_NOT_FOUND_CODE,
					CustomerFieldExceptionMessage.FIRST_NAME_NOT_FOUND_MESSAGE);
		}
		return customerDetailsDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demoapp.integrationtest.h2.services.CustomerService#
	 * searchCustomerByLastName()
	 */
	@Override
	public CustomerDetailsDTO searchCustomerByLastName(String lastName) throws Exception {

		CustomerDetailsDTO customerDetailsDTO = null;

		if (lastName != null) {
			Customer customer = customerInventoryDAO.searchCustomerByLastName(lastName);
			customerDetailsDTO = CustomerInventoryConverter.converEntityObjecttToDTOObject(customer);
		}else{
			throw new CustomerFieldException(CustomerFieldExceptionMessage.LAST_NAME_NOT_FOUND_CODE,
					CustomerFieldExceptionMessage.LAST_NAME_NOT_FOUND_MESSAGE);
		}

		return customerDetailsDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demoapp.integrationtest.h2.services.CustomerService#
	 * updateCustomerById(java.lang.String)
	 */
	@Override
	public void updateCustomer(CustomerDetailsVO customerDetailsVO) throws Exception {
		Customer customer = CustomerInventoryConverter.convertVOObjecttoEntityObject(customerDetailsVO);
		customerInventoryDAO.updateCustomer(customer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demoapp.integrationtest.h2.services.CustomerService#
	 * deleteCustomerById(java.lang.String)
	 */
	@Override
	public void deleteCustomer(CustomerDetailsVO customerDetailsVO) throws Exception {

		if (customerDetailsVO != null) {

			CustomerDetailsDTO customerDetailsDTO = CustomerInventoryConverter.converEntityObjecttToDTOObject(
					customerInventoryDAO.searchCustomerById(customerDetailsVO.getPersonalId()));

			if (customerDetailsDTO != null) {
				Customer customer = CustomerInventoryConverter.convertVOObjecttoEntityObject(customerDetailsVO);
				this.customerInventoryDAO.deleteCustomer(customer);
			} else {
				throw new CustomerFieldException(3, CustomerFieldExceptionMessage.CUSTOMER_DOES_NOT_EXIST_IN_DATABASE);
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
	 * @param customerInventoryDAO
	 *            the customerInventoryDAO to set
	 */
	public void setCustomerInventoryDAO(CustomerInventoryDAO customerInventoryDAO) {
		this.customerInventoryDAO = customerInventoryDAO;
	}

}
