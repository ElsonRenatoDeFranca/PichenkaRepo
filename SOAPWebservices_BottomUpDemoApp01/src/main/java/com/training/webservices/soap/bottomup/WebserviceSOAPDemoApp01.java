package com.training.webservices.soap.bottomup;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class WebserviceSOAPDemoApp01 {

	@WebMethod
	public String sayHello(){
		return "HelloWorld";
	}
}
