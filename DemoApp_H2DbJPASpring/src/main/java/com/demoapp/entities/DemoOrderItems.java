/**
 * 
 */
package com.demoapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author userpc
 *
 */

@Entity
@Table(name="DEMO_ORDER_ITEMS")
public class DemoOrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="ORDER_ITEM_ID")
	private Long id;
	
	@Column(name="ORDER_ID")
	private Long orderId;
	
	
}
