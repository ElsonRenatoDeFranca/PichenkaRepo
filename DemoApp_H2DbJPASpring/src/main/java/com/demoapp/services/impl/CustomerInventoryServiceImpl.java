package com.demoapp.services.impl;

import java.util.List;

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

	@Override
	public void addCustomer(CustomerDetailsVO customerDetailsVO) throws Exception {

		if (customerDetailsVO != null) {

			CustomerDetailsDTO customerDetailsDTO = CustomerInventoryConverter
					.converEntityObjecttToDTOObject(customerInventoryDAO.searchCustomerById(customerDetailsVO.getPersonalId()));

			if (customerDetailsDTO == null || customerDetailsDTO.getPersonalId()==null) {
				Customer customer = CustomerInventoryConverter.convertVOObjecttoEntityObject(customerDetailsVO);
				this.customerInventoryDAO.addCustomer(customer);
			} else {
				System.out.println("Customer already exists in the database");
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
			customerDetailsDTO = CustomerInventoryConverter
					.converEntityObjecttToDTOObject(customerInventoryDAO.searchCustomerById(personalId));

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

		if (firstName != null) {
			customerDetailsDTO = CustomerInventoryConverter
					.converEntityObjecttToDTOObject(customerInventoryDAO.searchCustomerByFirstName(firstName));
		}else{
			throw new CustomerFieldException(CustomerFieldExceptionMessage.FIRST_NAME_NOT_FOUND);
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
			customerDetailsDTO = CustomerInventoryConverter
					.converEntityObjecttToDTOObject(customerInventoryDAO.searchCustomerByLastName(lastName));
		}else{
			throw new CustomerFieldException(CustomerFieldExceptionMessage.LAST_NAME_NOT_FOUND);
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

			CustomerDetailsDTO customerDetailsDTO = CustomerInventoryConverter
					.converEntityObjecttToDTOObject(customerInventoryDAO.searchCustomerById(customerDetailsVO.getPersonalId()));

			if (customerDetailsDTO != null) {
				Customer customer = CustomerInventoryConverter.convertVOObjecttoEntityObject(customerDetailsVO);
				this.customerInventoryDAO.deleteCustomer(customer);
			} else {
				throw new CustomerFieldException(CustomerFieldExceptionMessage.CUSTOMER_DOES_NOT_EXIST_IN_DATABASE);
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
