package com.fhlbsfo.fta.controller;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhlbsfo.fta.AccountRepository;
import com.fhlbsfo.fta.entity.Account;
import com.fhlbsfo.fta.entity.FundsTransaction;
import com.fhlbsfo.fta.exceptions.AccountNotFoundException;
import com.fhlbsfo.fta.exceptions.AmountLessOrZeroException;
import com.fhlbsfo.fta.exceptions.NotEnoughFundsException;
import com.fhlbsfo.fta.util.Util;

@RestController
@Validated
public class AccountController {

	private static final Logger log = LoggerFactory.getLogger(AccountController.class);

	private final AccountRepository repository;
	private final Util util;

	AccountController(AccountRepository repository) {
		this.repository = repository;
		this.util = new Util();
	}

	// Get all accounts
	@GetMapping("/accounts")
	List<Account> all() {
		log.info("Getting all Accounts");
		return repository.findAll();
	}

	// Create new account
	@PostMapping("/accounts")
	public Account newAccount(@RequestBody Account newAccount) {
		return repository.save(newAccount);
	}

	// TODO: handle method argument exception
	// Get one account by row id
	@GetMapping("/accounts/{id}")
	public Account getOneAccount(@PathVariable @NotNull @Min(1) Integer id) {

		return repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
	}

	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<Object> deleteAccount(@PathVariable @NotNull @Min(0) Integer id) {
		log.info("Deleting account: " + id);
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error deleting Account: " + e.getMessage());
			return new ResponseEntity<>("Account deletion failed", HttpStatus.BAD_REQUEST);
		}
		log.info("Finished - Deleting account: " + id);
		return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);

	}

	@Transactional // Might be better to move to Service or DAO layer with explicit locking. For
					// simplicity all logic here instead of a service/dao layer
	@PutMapping("/transferFunds")
	public ResponseEntity<Object> transferFunds(@RequestBody FundsTransaction fundsTransaction,
			@RequestParam(value = "fee", defaultValue = "0") int fee)
			throws NotEnoughFundsException, AmountLessOrZeroException {

		// TODO: re-visit BigDecimal formatting - should be across the board. Implement optional fee logic
		log.info("Transfer funds: " + fundsTransaction.toString());

		double amount = util.formatTwoDecimal(fundsTransaction.getAmount());
		try {
			if (amount > 0.0d) {

				Account accountFrom = this.getOneAccount(fundsTransaction.getAccountFromId());
				Account accountTo = this.getOneAccount(fundsTransaction.getAccountToId());

				if (accountFrom.getBalance() > amount) {

					accountFrom.setBalance(util.formatTwoDecimal(accountFrom.getBalance() - amount));
					accountTo.setBalance(util.formatTwoDecimal(accountTo.getBalance() + amount));

				} else {
					throw new NotEnoughFundsException(accountFrom.getAccountId());
				}

				repository.save(accountFrom);
				repository.save(accountTo);

				log.info("Finished - Transfer funds");
			} else {
				throw new AmountLessOrZeroException(amount);

			}
		} catch (Exception e) {
			log.error("Error transferring funds: " + e.getMessage());
			return new ResponseEntity<>("Funds Transfer failed", HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<>("Funds Transfer successful", HttpStatus.OK);

	}

}
