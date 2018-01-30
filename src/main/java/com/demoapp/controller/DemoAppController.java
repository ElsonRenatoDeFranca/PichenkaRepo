package com.demoapp.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demoapp.services.CustomerInventoryService;
import com.demoapp.util.to.CustomerDetailsDTO;
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
			customerDetailsVO.setEmail("joe@gmail.com");
			customerDetailsVO.setCustomerId(1L);
			customerDetailsVO.setFirstName("john");
			customerDetailsVO.setLastName("doe");

			
			CustomerDetailsDTO customerDetailsTO = demoApp.customerService.searchCustomerById(customerDetailsVO);
			
			if(customerDetailsTO !=null){
				System.out.println(customerDetailsTO.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
