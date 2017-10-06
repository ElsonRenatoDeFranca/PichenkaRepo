/**
 * 
 */
package com.demoapp.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author userpc
 *
 */

@Entity
@Table(name="DEMO_ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="ORDER_ID")
	private Long id;
	
	@Column(name="ORDER_TOTAL")
	private Double total;
	
	@Column(name="ORDER_TIMESTAMP")
	private Date timestamp;
	
	private Customer customerId;
	
	private List<Product> products;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the customerId
	 */
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COMPANY_ID")
	@Column(name="CUSTOMER_TIMESTAMP")
	public Customer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the products
	 */
	@ManyToMany
    @JoinTable(name="DEMO_ORDER_ITEMS",
        joinColumns=
            @JoinColumn(name="ORDER_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="PRODUCT_ID", referencedColumnName="ID")
        )
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	

	
	
	
}
