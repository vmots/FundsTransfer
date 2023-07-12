package com.fhlbsfo.fta.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class FundsTransaction {
	
	@NotNull 
	@Min(1)
	public Integer accountFromId;
	
	@NotNull 
	@Min(1)
	public Integer accountToId;
	
	@NotNull 
	@Min(0)
	public double amount;
	
	public Integer getAccountFromId() {
		return accountFromId;
	}
	
	public FundsTransaction(Integer accountFromId, Integer accountToId, double amount){
		
		this.accountFromId = accountFromId;
		this.accountToId = accountToId;
		this.amount = amount;
		
	}
		
	
	
	public void setAccountFromId(Integer accountFromId) {
		this.accountFromId = accountFromId;
	}
	public Integer getAccountToId() {
		return accountToId;
	}
	public void setAccountToId(Integer accountToId) {
		this.accountToId = accountToId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	


}
