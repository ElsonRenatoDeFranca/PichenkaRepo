/**
 * 
 */
package com.training.webservices.soap.client;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.bharath.ws.trainings.CustomerOrdersPortType;
import com.bharath.ws.trainings.CustomerOrdersService;
import com.bharath.ws.trainings.GetOrdersRequest;
import com.bharath.ws.trainings.GetOrdersResponse;
import com.bharath.ws.trainings.Order;
import com.bharath.ws.trainings.Product;

/**
 * @author userpc
 *
 */
public class CustomerOrdersWSClient {

	public static void main(String[] args){
		String wsUrl="http://localhost:8080/wsdlfirstws/services/customerOrders?wsdl";
		try {
			CustomerOrdersService service = new CustomerOrdersService(new URL(wsUrl));
			CustomerOrdersPortType customerOrdersPort = service.getCustomerOrdersPort();
			GetOrdersRequest request = new GetOrdersRequest();
			request.setCustomerId(BigInteger.valueOf(1));
			GetOrdersResponse response = customerOrdersPort.getOrders(request);
			
			List<Order> orders = response.getOrder();
			
			for (Order order : orders) {
				List<Product> products = order.getProduct();
				for (Product product : products) {
					System.out.println("Product description: "+product.getDescription());
					System.out.println("Product quantity: "+product.getQuantity());
				}
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
