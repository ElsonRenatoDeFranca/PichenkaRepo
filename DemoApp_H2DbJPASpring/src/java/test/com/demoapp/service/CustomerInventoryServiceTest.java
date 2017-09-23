/**
 * 
 */
package com.demoapp.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Every.everyItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.demoapp.dao.CustomerInventoryDAO;
import com.demoapp.entities.Customer;
import com.demoapp.exception.CustomerFieldException;
import com.demoapp.exception.CustomerFieldExceptionMessage;
import com.demoapp.services.impl.CustomerInventoryServiceImpl;
import com.demoapp.util.dto.CustomerDetailsDTO;
import com.demoapp.util.vo.CustomerDetailsVO;

/**
 * @author userpc
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerInventoryServiceTest {

	/**
	 * 
	 */
	@Mock
	private CustomerInventoryDAO customerInventoryDAO;

	@InjectMocks
	private CustomerInventoryServiceImpl customerService = new CustomerInventoryServiceImpl();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setup() {
		customerService.setCustomerInventoryDAO(customerInventoryDAO);
	}

	@Test
	public void deleteCustomer_ShouldDeleteCustomer_whenCustomerIsPassedByParameter() throws Exception {

		// Given
		CustomerDetailsVO customerDetailsVO = new CustomerDetailsVO();
		customerDetailsVO.setEmail("manuelbandeira@gmail.com");
		customerDetailsVO.setFirstName("Manuel");
		customerDetailsVO.setLastName("Bandeira");
		customerDetailsVO.setPersonalId(10L);

		// When
		customerService.deleteCustomer(customerDetailsVO);

		ArgumentCaptor<Customer> argument = ArgumentCaptor.forClass(Customer.class);
		verify(customerInventoryDAO, Mockito.atLeastOnce()).deleteCustomer(argument.capture());

		assertThat(argument, not(nullValue()));
		assertThat(argument, hasProperty("value", not(nullValue())));
		assertThat(argument, hasProperty("value", hasProperty("personalId", not(nullValue()))));
		assertThat(argument, hasProperty("value", hasProperty("firstName", not(nullValue()))));
		assertThat(argument, hasProperty("value", hasProperty("lastName", not(nullValue()))));
		assertThat(argument, hasProperty("value", hasProperty("customerEmail", not(nullValue()))));

		assertThat(argument, hasProperty("value", hasProperty("personalId", equalTo(10L))));
		assertThat(argument, hasProperty("value", hasProperty("firstName", equalTo("Manuel"))));
		assertThat(argument, hasProperty("value", hasProperty("lastName", equalTo("Bandeira"))));
		assertThat(argument, hasProperty("value", hasProperty("customerEmail", equalTo("manuelbandeira@gmail.com"))));

	}

	@Test
	public void updateCustomer_ShouldUpdateCustomer_whenCustomerIsPassedByParameter() throws Exception {
		// Given
		CustomerDetailsVO customerDetailsVO = new CustomerDetailsVO();
		customerDetailsVO.setEmail("manuelbandeira@gmail.com");
		customerDetailsVO.setFirstName("Manuel");
		customerDetailsVO.setLastName("Bandeira");
		customerDetailsVO.setPersonalId(10L);

		// When
		customerService.updateCustomer(customerDetailsVO);

		ArgumentCaptor<Customer> argument = ArgumentCaptor.forClass(Customer.class);
		verify(customerInventoryDAO, Mockito.atLeastOnce()).updateCustomer(argument.capture());

		assertThat(argument, not(nullValue()));
		assertThat(argument, hasProperty("value", not(nullValue())));
		assertThat(argument, hasProperty("value", hasProperty("personalId", not(nullValue()))));
		assertThat(argument, hasProperty("value", hasProperty("firstName", not(nullValue()))));
		assertThat(argument, hasProperty("value", hasProperty("lastName", not(nullValue()))));
		assertThat(argument, hasProperty("value", hasProperty("customerEmail", not(nullValue()))));

		assertThat(argument, hasProperty("value", hasProperty("personalId", equalTo(10L))));
		assertThat(argument, hasProperty("value", hasProperty("firstName", equalTo("Manuel"))));
		assertThat(argument, hasProperty("value", hasProperty("lastName", equalTo("Bandeira"))));
		assertThat(argument, hasProperty("value", hasProperty("customerEmail", equalTo("manuelbandeira@gmail.com"))));

	}

	@Test
	public void addCustomer_ShouldPersistCustomer_whenCustomerIsPassedByParameter() throws Exception {

		// Given
		CustomerDetailsVO customerDetailsVO = new CustomerDetailsVO();
		customerDetailsVO.setEmail("antoniosolanolopez@gmail.com");
		customerDetailsVO.setFirstName("Antonio");
		customerDetailsVO.setLastName("Solano Lopez");
		customerDetailsVO.setPersonalId(1L);

		// When
		customerService.addCustomer(customerDetailsVO);

		ArgumentCaptor<Customer> argument = ArgumentCaptor.forClass(Customer.class);
		verify(customerInventoryDAO, Mockito.atLeastOnce()).addCustomer(argument.capture());

		assertThat(argument, not(nullValue()));
		assertThat(argument, hasProperty("value", not(nullValue())));
		assertThat(argument, hasProperty("value", hasProperty("personalId", not(nullValue()))));
		assertThat(argument, hasProperty("value", hasProperty("firstName", not(nullValue()))));
		assertThat(argument, hasProperty("value", hasProperty("lastName", not(nullValue()))));
		assertThat(argument, hasProperty("value", hasProperty("customerEmail", not(nullValue()))));

		assertThat(argument, hasProperty("value", hasProperty("personalId", equalTo(1L))));
		assertThat(argument, hasProperty("value", hasProperty("firstName", equalTo("Antonio"))));
		assertThat(argument, hasProperty("value", hasProperty("lastName", equalTo("Solano Lopez"))));
		assertThat(argument,
				hasProperty("value", hasProperty("customerEmail", equalTo("antoniosolanolopez@gmail.com"))));

	}

	@Test
	public void searchAll_ShouldReturnCustomerList_WhenThereIsACustomerThatMatchesTheCriterion() throws Exception {

		// Given
		Customer customer = new Customer();
		customer.setCustomerEmail("brucewayne@gmail.com");
		customer.setFirstName("Bruce");
		customer.setLastName("Wayne");
		customer.setPersonalId(20L);

		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer);

		// When
		when(customerInventoryDAO.searchAll()).thenReturn(customers);

		List<CustomerDetailsDTO> customerDetailsList = customerService.searchAll();

		// Then
		assertThat(customerDetailsList, not(nullValue()));
		assertThat(customerDetailsList, hasSize(greaterThan(0)));
		assertThat(customerDetailsList, everyItem(hasProperty("personalId", not(nullValue()))));
		assertThat(customerDetailsList, everyItem(hasProperty("firstName", not(nullValue()))));
		assertThat(customerDetailsList, everyItem(hasProperty("lastName", not(nullValue()))));
		assertThat(customerDetailsList, everyItem(hasProperty("email", not(nullValue()))));

		assertThat(customerDetailsList, everyItem(hasProperty("personalId", equalTo(20L))));
		assertThat(customerDetailsList, everyItem(hasProperty("firstName", equalTo("Bruce"))));
		assertThat(customerDetailsList, everyItem(hasProperty("lastName", equalTo("Wayne"))));
		assertThat(customerDetailsList, everyItem(hasProperty("email", equalTo("brucewayne@gmail.com"))));
	}

	@Test
	public void searchCustomerByFirstName_ShouldReturnCustomer_WhenThereIsACustomerThatMatchesTheCriterion()
			throws Exception {
		Customer customer = new Customer();
		customer.setCustomerEmail("antoniosolanolopez@gmail.com");
		customer.setFirstName("Antonio");
		customer.setLastName("Solano Lopez");
		customer.setPersonalId(1L);

		// When
		when(customerInventoryDAO.searchCustomerByFirstName(Mockito.anyString())).thenReturn(customer);
		CustomerDetailsDTO customerDetailsDTO = customerService.searchCustomerByFirstName(customer.getFirstName());

		// Then
		assertThat(customerDetailsDTO, not(nullValue()));
		assertThat(customerDetailsDTO, hasProperty("personalId", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("firstName", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("lastName", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("email", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("personalId", equalTo(1L)));
		assertThat(customerDetailsDTO, hasProperty("firstName", equalTo("Antonio")));
		assertThat(customerDetailsDTO, hasProperty("lastName", equalTo("Solano Lopez")));
		assertThat(customerDetailsDTO, hasProperty("email", equalTo("antoniosolanolopez@gmail.com")));

	}

	@Test
	public void searchCustomerByLastName_ShouldReturnCustomer_WhenLastNameIsNotNull() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerEmail("antoniosolanolopez@gmail.com");
		customer.setFirstName("Antonio");
		customer.setLastName("Solano Lopez");
		customer.setPersonalId(1L);

		// When
		when(customerInventoryDAO.searchCustomerByLastName(Mockito.anyString())).thenReturn(customer);

		CustomerDetailsDTO customerDetailsDTO = customerService.searchCustomerByLastName(customer.getLastName());

		// Then
		assertThat(customerDetailsDTO, not(nullValue()));
		assertThat(customerDetailsDTO, hasProperty("personalId", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("firstName", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("lastName", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("email", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("personalId", equalTo(1L)));
		assertThat(customerDetailsDTO, hasProperty("firstName", equalTo("Antonio")));
		assertThat(customerDetailsDTO, hasProperty("lastName", equalTo("Solano Lopez")));
		assertThat(customerDetailsDTO, hasProperty("email", equalTo("antoniosolanolopez@gmail.com")));

	}

	@Test
	public void searchCustomerById_ShouldReturnCustomer_WhenThereIsACustomerThatMatchesTheCriterion() throws Exception {
		// Given
		Customer customer = new Customer();
		customer.setCustomerEmail("antoniosolanolopez@gmail.com");
		customer.setFirstName("Antonio");
		customer.setLastName("Solano Lopez");
		customer.setPersonalId(1L);

		// When
		when(customerInventoryDAO.searchCustomerById(Mockito.anyLong())).thenReturn(customer);

		CustomerDetailsDTO customerDetailsDTO = customerService.searchCustomerById(Mockito.anyLong());

		// Then
		assertThat(customerDetailsDTO, not(nullValue()));
		assertThat(customerDetailsDTO, hasProperty("personalId", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("firstName", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("lastName", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("email", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("personalId", equalTo(1L)));
		assertThat(customerDetailsDTO, hasProperty("firstName", equalTo("Antonio")));
		assertThat(customerDetailsDTO, hasProperty("lastName", equalTo("Solano Lopez")));
		assertThat(customerDetailsDTO, hasProperty("email", equalTo("antoniosolanolopez@gmail.com")));

	}

	@Test
	public void searchCustomerByFirstName_ShouldNotReturnCustomer_WhenFirstNameIsNull() throws Exception {
		expectedException.expect(CustomerFieldException.class);
		expectedException.expectMessage(is(CustomerFieldExceptionMessage.FIRST_NAME_NOT_FOUND_MESSAGE));
		expectedException.expect(hasProperty("errCode"));
		expectedException.expect(hasProperty("errCode", is(CustomerFieldExceptionMessage.FIRST_NAME_NOT_FOUND_CODE)));
		
		customerService.searchCustomerByFirstName(null);
		verify(customerInventoryDAO, Mockito.atLeastOnce()).searchCustomerByFirstName(null);
	}

	@Test
	public void searchCustomerById_ShouldNotReturnCustomer_WhenCustomerIdIsNull() throws Exception {
		
		expectedException.expect(CustomerFieldException.class);
		expectedException.expectMessage(is(CustomerFieldExceptionMessage.CUSTOMER_ID_NOT_FOUND_MESSAGE));
		expectedException.expect(hasProperty("errCode"));
		expectedException.expect(hasProperty("errCode", is(CustomerFieldExceptionMessage.CUSTOMER_ID_NOT_FOUND_CODE)));

		customerService.searchCustomerById(null);
		verify(customerInventoryDAO, Mockito.atLeastOnce()).searchCustomerById(null);
	}
	
	@Test
	public void searchCustomerByLastName_ShouldNotReturnCustomer_WhenCustomerLastIsNull() throws Exception {
		
		expectedException.expect(CustomerFieldException.class);
		expectedException.expectMessage(is(CustomerFieldExceptionMessage.LAST_NAME_NOT_FOUND_MESSAGE));
		expectedException.expect(hasProperty("errCode"));
		expectedException.expect(hasProperty("errCode", is(CustomerFieldExceptionMessage.LAST_NAME_NOT_FOUND_CODE)));

		customerService.searchCustomerByLastName(null);
		verify(customerInventoryDAO, Mockito.atLeastOnce()).searchCustomerByLastName(null);
	}
	
	@Test
	public void addCustomer_ShouldReturnCustomerAlreadyExistsException_WhenCustomerIsAlreadyInDatabase() throws Exception {
		
		// Given
		CustomerDetailsVO customerDetailsVO = new CustomerDetailsVO();
		customerDetailsVO.setEmail("antoniosolanolopez@gmail.com");
		customerDetailsVO.setFirstName("Antonio");
		customerDetailsVO.setLastName("Solano Lopez");
		customerDetailsVO.setPersonalId(1L);
		
		Customer customer = new Customer();
		customer.setCustomerEmail("antoniosolanolopez@gmail.com");
		customer.setFirstName("Antonio");
		customer.setLastName("Solano Lopez");
		customer.setPersonalId(1L);

		expectedException.expect(CustomerFieldException.class);
		expectedException.expectMessage(is(CustomerFieldExceptionMessage.CUSTOMER_ALREADY_EXISTS_MESSAGE));
		expectedException.expect(hasProperty("errCode"));
		expectedException.expect(hasProperty("errCode", is(CustomerFieldExceptionMessage.CUSTOMER_ALREADY_EXISTS_CODE)));
		//When
		when(customerInventoryDAO.searchCustomerById(Mockito.eq(customerDetailsVO.getPersonalId()))).thenReturn(customer);

		customerService.addCustomer(customerDetailsVO);
		verify(customerInventoryDAO, Mockito.atLeastOnce()).addCustomer(customer);
	}



}
