/**
 * 
 */
package com.fhlbsfo.fta.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class Account {

	Account(){}

	public Account(Integer accountId, double balance) {

		this.accountId = accountId;
		this.balance = balance;
	}
	
	

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer id) {
		this.accountId = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@NotNull
	@Min(0)
	private @Id Integer accountId;
	
	@NotNull
	@Min(0)
	private double balance;

	
	 @Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof Account))
	      return false;
	    Account account = (Account) o;
	    return Objects.equals(this.accountId, account.accountId) 
	        && Objects.equals(this.balance, account.balance);
	  }

	
	@Override
	public int hashCode() {
		return Objects.hash(this.accountId, this.balance);
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + "]";
	}

	
}
