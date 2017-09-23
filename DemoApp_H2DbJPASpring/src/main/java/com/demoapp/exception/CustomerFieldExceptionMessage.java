/**
 * 
 */
package com.demoapp.exception;

/**
 * @author userpc
 *
 */
public interface CustomerFieldExceptionMessage {
	

	
	/**
	 * 
	 */
	public static final String CUSTOMER_ID_NOT_FOUND_MESSAGE="Customer ID cannot not found";
	/**
	 * 
	 */
	public static final int CUSTOMER_ID_NOT_FOUND_CODE=1;
	
	/**
	 * 
	 */
	public static final String FIRST_NAME_NOT_FOUND_MESSAGE="First name cannot be null";
	
	/**
	 * 
	 */
	public static final int FIRST_NAME_NOT_FOUND_CODE =2;
	/**
	 * 
	 */
	public static final String LAST_NAME_NOT_FOUND_MESSAGE="Last name cannot be null";
	
	/**
	 * 
	 */
	public static final int LAST_NAME_NOT_FOUND_CODE =3;

	/**
	 * 
	 */
	public static final String CUSTOMER_ALREADY_EXISTS_MESSAGE="Customer already exists in the database";
	
	/**
	 * 
	 */
	public static final int CUSTOMER_ALREADY_EXISTS_CODE=4;
	
	/**
	 * 
	 */
	public static final String CUSTOMER_DOES_NOT_EXIST_IN_DATABASE="Customer does not exist in database";



}
