package com.demoapp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.demoapp.dao.CustomerInventoryDAO;
import com.demoapp.entities.Customer;

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
	private EntityManagerFactory entityManagerFactory;
	/**
	 * 
	 */
	private EntityManager entityManager;
	
	
	 /**
	 * 
	 */
	public CustomerInventoryDAOImpl(){
		this.entityManagerFactory=Persistence.createEntityManagerFactory("myPersistentUnit");
		this.entityManager = entityManagerFactory.createEntityManager();
	}


	@Override
	public void addCustomer(Customer customer) throws Exception {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistentUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(customer);
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
	public List<Customer> searchAll() throws Exception {
    	
		List<Customer> customerList = this.entityManager
				.createQuery("Select a From Customer a", Customer.class).getResultList();
		
		return customerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demoapp.integrationtest.h2.dao.CustomerDAO#searchCustomerById()
	 */
	@Override
	public Customer searchCustomerById(Long personalId) throws Exception {

		Customer customer = entityManager.find(Customer.class, personalId);
		return customer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demoapp.integrationtest.h2.dao.CustomerDAO#searchCustomerByFirstName(
	 * )
	 */
	@Override
	public Customer searchCustomerByFirstName(String firstName) throws Exception {
		Query query = this.entityManager.createQuery("SELECT c FROM Customer c WHERE c.firstName = "+"'"+firstName+"'");
		Customer customer = (Customer) query.getSingleResult();
		return customer;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demoapp.integrationtest.h2.dao.CustomerDAO#searchCustomerByLastName()
	 */
	@Override
	public Customer searchCustomerByLastName(String lastName) throws Exception {

		Query query = this.entityManager.createQuery("SELECT c FROM Customer c WHERE c.lastName ="+"'"+lastName+"'");
		Customer customer = (Customer) query.getSingleResult();

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
	public void updateCustomer(Customer customer) throws Exception {
		
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(customer);
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
	public void deleteCustomer(Customer customer) throws Exception {

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
