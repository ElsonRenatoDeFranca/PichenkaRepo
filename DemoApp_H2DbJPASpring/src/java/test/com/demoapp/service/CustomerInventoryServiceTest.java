/**
 * 
 */
package com.demoapp.service;

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
import org.junit.Ignore;
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
		customerDetailsVO.setEmail("angelavieira@gmail.com");
		customerDetailsVO.setFirstName("Angela");
		customerDetailsVO.setLastName("Vieira");
		customerDetailsVO.setPersonalId(9L);

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

		assertThat(argument, hasProperty("value", hasProperty("personalId", equalTo(9L))));
		assertThat(argument, hasProperty("value", hasProperty("firstName", equalTo("Angela"))));
		assertThat(argument, hasProperty("value", hasProperty("lastName", equalTo("Vieira"))));
		assertThat(argument, hasProperty("value", hasProperty("customerEmail", equalTo("angelavieira@gmail.com"))));

	}

	@Test
	public void searchAll_ShouldReturnCustomerList_WhenThereIsACustomerThatMatchesTheCriterion() throws Exception {

		// Given
		Customer customer = new Customer();
		customer.setCustomerEmail("carlosxaviersilva@gmail.com");
		customer.setFirstName("Carlos");
		customer.setLastName("Xavier da Silva");
		customer.setPersonalId(7L);

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

		assertThat(customerDetailsList, everyItem(hasProperty("personalId", equalTo(7L))));
		assertThat(customerDetailsList, everyItem(hasProperty("firstName", equalTo("Carlos"))));
		assertThat(customerDetailsList, everyItem(hasProperty("lastName", equalTo("Xavier da Silva"))));
		assertThat(customerDetailsList, everyItem(hasProperty("email", equalTo("carlosxaviersilva@gmail.com"))));
	}

	@Test
	public void searchCustomerByFirstName_ShouldReturnCustomer_WhenThereIsACustomerThatMatchesTheCriterion()
			throws Exception {
		// Given
		Customer customer = new Customer();
		customer.setCustomerEmail("manuelsilva@gmail.com");
		customer.setFirstName("Manuel");
		customer.setLastName("Santos Neves");
		customer.setPersonalId(1L);

		// When
		when(customerInventoryDAO.searchCustomerByFirstName(Mockito.anyString())).thenReturn(customer);

		CustomerDetailsDTO customerDetailsDTO = customerService.searchCustomerByFirstName(Mockito.anyString());

		// Then
		assertThat(customerDetailsDTO, not(nullValue()));
		assertThat(customerDetailsDTO, hasProperty("personalId", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("firstName", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("lastName", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("email", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("personalId", equalTo(1L)));
		assertThat(customerDetailsDTO, hasProperty("firstName", equalTo("Manuel")));
		assertThat(customerDetailsDTO, hasProperty("lastName", equalTo("Santos Neves")));
		assertThat(customerDetailsDTO, hasProperty("email", equalTo("manuelsilva@gmail.com")));

	}

	@Test
	public void searchCustomerByLastName_ShouldReturnCustomer_WhenThereIsACustomerThatMatchesTheCriterion()
			throws Exception {
		// Given
		Customer customer = new Customer();
		customer.setCustomerEmail("pedroalvarescabral@gmail.com");
		customer.setFirstName("Pedro");
		customer.setLastName("Alvares Cabral");
		customer.setPersonalId(3L);

		// When
		when(customerInventoryDAO.searchCustomerByLastName(Mockito.anyString())).thenReturn(customer);

		CustomerDetailsDTO customerDetailsDTO = customerService.searchCustomerByLastName(Mockito.anyString());

		// Then
		assertThat(customerDetailsDTO, not(nullValue()));
		assertThat(customerDetailsDTO, hasProperty("personalId", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("firstName", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("lastName", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("email", not(nullValue())));
		assertThat(customerDetailsDTO, hasProperty("personalId", equalTo(3L)));
		assertThat(customerDetailsDTO, hasProperty("firstName", equalTo("Pedro")));
		assertThat(customerDetailsDTO, hasProperty("lastName", equalTo("Alvares Cabral")));
		assertThat(customerDetailsDTO, hasProperty("email", equalTo("pedroalvarescabral@gmail.com")));

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

	@Ignore
	@SuppressWarnings("unchecked")
	@Test(expected = CustomerFieldException.class)
	public void searchCustomerByFirstName_ShouldNotReturnCustomer_WhenNullIsPassedAsSearchParameter() throws Exception {

		Customer customer = new Customer();
		customer.setCustomerEmail("manuelsilva@gmail.com");
		customer.setFirstName("Manuel");
		customer.setLastName("Santos Neves");
		customer.setPersonalId(1L);

		// When
		when(customerInventoryDAO.searchCustomerByFirstName(null)).thenThrow(CustomerFieldException.class);
		expectedException.expect(Exception.class);
		expectedException.expectMessage(CustomerFieldExceptionMessage.FIRST_NAME_NOT_FOUND);

		customerService.setCustomerInventoryDAO(customerInventoryDAO);

		CustomerDetailsDTO customerDetailsDTO = customerService.searchCustomerByFirstName(null);

	}

}
