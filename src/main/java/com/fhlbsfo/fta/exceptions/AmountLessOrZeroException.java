package com.fhlbsfo.fta.exceptions;

public class AmountLessOrZeroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AmountLessOrZeroException(double amount) {
		// TODO Auto-generated constructor stub
		super("Amount cannot be equal or less than 0: " + amount);
	}

}
