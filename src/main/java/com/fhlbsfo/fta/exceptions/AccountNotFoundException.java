package com.fhlbsfo.fta.exceptions;

public class AccountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(Integer accountId) {
		// TODO Auto-generated constructor stub
	super("Account does not exist: " + accountId);
	
	}
}
