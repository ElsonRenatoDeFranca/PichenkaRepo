package com.demoapp.controller;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demoapp.services.CustomerInventoryService;
import com.demoapp.util.dto.CustomerDetailsDTO;
import com.demoapp.util.vo.CustomerDetailsVO;

public class DemoAppController {

	/**
	 * 
	 */
	private CustomerInventoryService customerService;
	
	/**
	 * 
	 */
	private ClassPathXmlApplicationContext applicationContext;
	
	/**
	 * 
	 */
	public DemoAppController(){
		applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
		customerService=(CustomerInventoryService)applicationContext.getBean("customerInventoryService",CustomerInventoryService.class);
	}
	
	public static void main(String[] args){
		
		DemoAppController demoApp = new DemoAppController();
		
		try {
			
			CustomerDetailsVO customerDetailsVO = new CustomerDetailsVO();
			customerDetailsVO.setEmail("carlosalbertonobrega@gmail.com");
			customerDetailsVO.setFirstName("Carlos");
			customerDetailsVO.setLastName("Alberto de Nobrega");
			customerDetailsVO.setPersonalId(6L);
			
			demoApp.customerService.addCustomer(customerDetailsVO);
			List<CustomerDetailsDTO> customerList = demoApp.customerService.searchAll();
			CustomerDetailsDTO customerDetailsByID = demoApp.customerService.searchCustomerById(1L);
			CustomerDetailsDTO customerDetailsByFirstName = demoApp.customerService.searchCustomerByFirstName("Edson");
			CustomerDetailsDTO customerDetailsByLastName = demoApp.customerService.searchCustomerByLastName("Santos Neves");
				
			System.out.println(customerDetailsByID.toString());
			System.out.println(customerDetailsByFirstName.toString());
			System.out.println(customerDetailsByLastName.toString());
			System.out.println(customerList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
