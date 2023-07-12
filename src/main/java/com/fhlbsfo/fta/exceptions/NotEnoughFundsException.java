package com.fhlbsfo.fta.exceptions;


public class NotEnoughFundsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughFundsException(Integer accountId) {
		// TODO Auto-generated constructor stub
		super("Not enough funds to transfer from account: " + accountId);

	}

}
