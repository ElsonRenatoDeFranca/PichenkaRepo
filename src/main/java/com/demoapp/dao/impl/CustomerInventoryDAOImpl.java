package com.demoapp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.demoapp.dao.CustomerInventoryDAO;
import com.demoapp.entities.Customer;
import com.demoapp.util.to.CustomerDetailsDTO;
import com.demoapp.util.vo.CustomerDetailsVO;

/**
 * 
 * 
 *
 */
@Component
public class CustomerInventoryDAOImpl implements CustomerInventoryDAO {

	/**
	 * 
	 */
	private EntityManager entityManager;

	/**
	 * 
	 */
	private EntityManagerFactory entityManagerFactory;

	/**
	 * 
	 */
	public CustomerInventoryDAOImpl(){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistentUnit");
		this.entityManager = entityManagerFactory.createEntityManager();
	}


	@Override
	public void addCustomer(CustomerDetailsVO customerDetailsVO) throws Exception {

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(customerDetailsVO);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demoapp.integrationtest.h2.dao.CustomerDAO#getAllCustomers()
	 */
	@Override
	public List<CustomerDetailsDTO> searchAll() throws Exception {

		List<CustomerDetailsDTO> customers = new ArrayList<CustomerDetailsDTO>();
    	
		List<Customer> customerList = this.entityManager
				.createQuery("Select a From Customer a", Customer.class).getResultList();

	    if(customerList != null){
	    	
	    	for(Customer customer: customerList){
	    		CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
	    		customerDetailsDTO.setCustomerId(customer.getCustomerId());
	    		customerDetailsDTO.setEmail(customer.getEmail());
	    		customerDetailsDTO.setFirstName(customer.getFirstName());
				customerDetailsDTO.setLastName(customer.getLastName());
				customers.add(customerDetailsDTO);
	    	}
	    	
	    }
		return customers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demoapp.integrationtest.h2.dao.CustomerDAO#searchCustomerById()
	 */
	@Override
	public CustomerDetailsDTO searchCustomerById(Long customerId) throws Exception {

		CustomerDetailsDTO customerDetailsDTO = null;

		Customer customer = entityManager.find(Customer.class, customerId);

		if (customer != null) {
			customerDetailsDTO = new CustomerDetailsDTO();
			customerDetailsDTO.setEmail(customer.getEmail());
			customerDetailsDTO.setFirstName(customer.getFirstName());
			customerDetailsDTO.setLastName(customer.getLastName());
		}

		return customerDetailsDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demoapp.integrationtest.h2.dao.CustomerDAO#searchCustomerByFirstName(
	 * )
	 */
	@Override
	public CustomerDetailsDTO searchCustomerByFirstName(String firstName) throws Exception {

		Query query = this.entityManager.createQuery("SELECT c FROM Customer c WHERE c.firstName = 'firstName'");
		CustomerDetailsDTO customer = (CustomerDetailsDTO) query.getSingleResult();

		return customer;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demoapp.integrationtest.h2.dao.CustomerDAO#searchCustomerByLastName()
	 */
	@Override
	public CustomerDetailsDTO searchCustomerByLastName(String lastName) throws Exception {

		Query query = this.entityManager.createQuery("SELECT c FROM Customer c WHERE c.lastName = 'lastName'");
		CustomerDetailsDTO customer = (CustomerDetailsDTO) query.getSingleResult();

		return customer;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demoapp.integrationtest.h2.dao.CustomerDAO#updateCustomerById(java.
	 * lang.String)
	 */
	@Override
	public void updateCustomerById(CustomerDetailsVO customerDetailsVO) throws Exception {
		CustomerDetailsDTO customerDetailsDTO = null;

		try {
			entityManager.getTransaction().begin();
			customerDetailsDTO = entityManager.merge(customerDetailsDTO);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demoapp.integrationtest.h2.dao.CustomerDAO#deleteCustomerById(java.
	 * lang.String)
	 */
	@Override
	public void deleteCustomerById(Long customerId) throws Exception {

		Customer customer = entityManager.find(Customer.class, customerId);
		entityManager.getTransaction().begin();
		entityManager.remove(customer);
		entityManager.getTransaction().commit();

	}

	/**
	 * 
	 * @return
	 */
	public EntityManager getEntityManager() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("myPersistentUnit");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	
		return entityManager;
	}

	/**
	 * 
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * 
	 * @return
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	/**
	 * 
	 * @param entityManagerFactory
	 */
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}


}
