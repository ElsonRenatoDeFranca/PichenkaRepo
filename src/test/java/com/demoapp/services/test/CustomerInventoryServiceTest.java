/**
 * 
 */
package com.demoapp.services.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.demoapp.dao.CustomerInventoryDAO;
import com.demoapp.services.CustomerInventoryService;
import com.demoapp.services.impl.CustomerInventoryServiceImpl;
import com.demoapp.util.to.CustomerDetailsDTO;
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
	private CustomerInventoryDAO customerDAOMock;
	
	/**
	 * 
	 */
	@InjectMocks
	private CustomerInventoryService customerService = new CustomerInventoryServiceImpl();

	/**
	 * 
	 */
	private CustomerDetailsDTO customerDetailsDTO;

	/**
	 * 
	 */
	private CustomerDetailsVO customerDetailsVO;

	
	@Before
	public void setup() {
		
		customerDetailsDTO = new CustomerDetailsDTO();
		customerDetailsDTO.setEmail("maju@gmail.com");
		customerDetailsDTO.setCustomerId(1L);
		customerDetailsDTO.setFirstName("Maria Julia");
		customerDetailsDTO.setLastName("Coutinho");
		
		customerDetailsVO = new CustomerDetailsVO();
		customerDetailsVO.setEmail("maju@gmail.com");
		customerDetailsVO.setCustomerId(1L);
		customerDetailsVO.setFirstName("Maria Julia");
		customerDetailsVO.setLastName("Coutinho");
		

	}

	@Test
	public void addCustomer_shouldReturnAddedCustomer_whenCustomerWasAddedSuccessfully() throws Exception{
			
		this.customerService.addCustomer(customerDetailsVO);;
		
		ArgumentCaptor<CustomerDetailsVO> argument = ArgumentCaptor.forClass(CustomerDetailsVO.class);
		verify(customerDAOMock).addCustomer(argument.capture());
		
		assertThat(argument.getValue(), is(not(nullValue())));
		assertThat(argument.getValue().getCustomerId(), is(not(nullValue())));
		assertThat(argument.getValue().getFirstName(), is(not(nullValue())));
		assertThat(argument.getValue().getLastName(), is(not(nullValue())));
		
		assertThat(argument.getValue().getCustomerId(), is(1L));
		assertThat(argument.getValue().getFirstName(), is("Maria Julia"));
		assertThat(argument.getValue().getLastName(), is("Coutinho"));
	
	}

	/**
	 * Update the customer 
	 * @throws Exception
	 */
	@Test
	public void updateCustomer_shouldReturnUpdatedCustomer_whenCustomerWasUpdatedSuccessfully() throws Exception{
		
		CustomerDetailsVO updatedCustomer = new CustomerDetailsVO();
		updatedCustomer.setEmail("mariajoaquina@gmail.com");
		updatedCustomer.setCustomerId(2L);
		updatedCustomer.setFirstName("Maria Joaquina");
		updatedCustomer.setLastName("Soares");
		

		this.customerService.updateCustomerById(updatedCustomer);
		
		ArgumentCaptor<CustomerDetailsVO> argument = ArgumentCaptor.forClass(CustomerDetailsVO.class);
		verify(customerDAOMock).updateCustomerById(argument.capture());
		
		assertThat(argument.getValue(), is(not(nullValue())));
		assertThat(argument.getValue().getCustomerId(), is(not(nullValue())));
		assertThat(argument.getValue().getFirstName(), is(not(nullValue())));
		assertThat(argument.getValue().getLastName(), is(not(nullValue())));
		
		assertThat(argument.getValue().getCustomerId(), is(2L));
		assertThat(argument.getValue().getFirstName(), is("Maria Joaquina"));
		assertThat(argument.getValue().getLastName(), is("Soares"));
		
	}
		
	/**
	 * Validates the scenario where there is no customer in the list
	 * @throws Exception
	 */
	@Test
	public void testSearchAll_whenThereIsNoCustomer_shouldReturnAnEmptyCustomerList() throws Exception {
		List<CustomerDetailsDTO> customerList = new ArrayList<CustomerDetailsDTO>();
		
		when(customerDAOMock.searchAll()).thenReturn(customerList);
		
		assertThat(customerList, is(not(nullValue())));
		assertThat(customerList, is(empty()));
		
	}
	
	/**
	 * Validates the scenario where there is at least one customer in the list
	 * @throws Exception
	 */
	@Test
	public void testSearchAll_whenThereIsAtLeastOneCustomer_shouldReturnCustomerList() throws Exception {
		List<CustomerDetailsDTO> customerList = new ArrayList<CustomerDetailsDTO>();
		customerList.add(customerDetailsDTO);
		
		when(customerDAOMock.searchAll()).thenReturn(customerList);
		
		assertThat(customerList, is(not(nullValue())));
		assertThat(customerList, is(not(empty())));
		assertThat(customerList, hasSize(greaterThan(0)));		

	}
	
	/**
	 * Validates the scenario where the search key is the <customerId> and this customer
	 * is available.
	 * @throws Exception
	 */
	@Test
	public void searchCustomerById_whenIdIsAvailable_shouldReturnASpecificId() throws Exception {
		
		when(customerDAOMock.searchCustomerById(customerDetailsVO.getCustomerId())).thenReturn(customerDetailsDTO);
		
		assertThat(customerDetailsDTO, is(not(nullValue())));
		assertThat(customerDetailsDTO.getCustomerId(), is(not(nullValue())));
		assertThat(customerDetailsDTO.getCustomerId(), is(1L));
	}
	
	/**
	 * Validates the scenario where the search key is the customer <firstName> and this customer
	 * is available.
	 * @throws Exception
	 */
	@Test
	public void searchCustomerByFirstName_whenFirstNameIsAvailable_shouldReturnASpecificFirstName() throws Exception {
		
		when(customerDAOMock.searchCustomerByFirstName(customerDetailsVO.getFirstName())).thenReturn(customerDetailsDTO);
		
		assertThat(customerDetailsDTO, is(not(nullValue())));
		assertThat(customerDetailsDTO.getFirstName(), is(not(nullValue())));
		assertThat(customerDetailsDTO.getFirstName(), is("Maria Julia"));
		
	}
	
	/**
	 * Validates the scenario where the search key is the customer <lastName> and this customer
	 * is available
	 * @throws Exception
	 */
	@Test
	public void searchCustomerByLastName_whenLastNameIsAvailable_shouldReturnASpecificLastName() throws Exception {
		
		when(customerDAOMock.searchCustomerByLastName(customerDetailsVO.getLastName())).thenReturn(customerDetailsDTO);
		
		assertThat(customerDetailsDTO, is(not(nullValue())));
		assertThat(customerDetailsDTO.getLastName(), is(not(nullValue())));
		assertThat(customerDetailsDTO.getLastName(), is("Coutinho"));	
	}
	


}
