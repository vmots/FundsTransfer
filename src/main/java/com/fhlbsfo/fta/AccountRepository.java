package com.fhlbsfo.fta;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhlbsfo.fta.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Integer>{

}
