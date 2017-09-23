/**
 * 
 */
package com.demoapp.exception;

/**
 * @author userpc
 *
 */
public class CustomerFieldException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
    private int errCode;

	
	public CustomerFieldException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

	/**
	 * 
	 * @return
	 */
	public int getErrCode() {
		return errCode;
	}

	/**
	 * 
	 * @param errCode
	 */
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	
}
