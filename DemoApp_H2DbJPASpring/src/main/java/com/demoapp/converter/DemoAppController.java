package com.demoapp.converter;

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
			customerDetailsVO.setEmail("maju@gmail.com");
			customerDetailsVO.setFirstName("Joao da Silva");
			customerDetailsVO.setLastName("Coutinho de Franca");
			customerDetailsVO.setPersonalId(1L);
			customerDetailsVO.setTelephone("+5541995031617");
			
			
			CustomerDetailsDTO customerDetailsTO = demoApp.customerService.searchCustomerByFirstName("maju");
			List<CustomerDetailsDTO> customerList = demoApp.customerService.searchAll();
			//demoApp.customerService.addCustomer(customerDetailsVO);
			
			if(customerDetailsTO !=null){
				System.out.println(customerDetailsTO.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
