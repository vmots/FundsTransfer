package com.fhlbsfo.fta.data;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fhlbsfo.fta.AccountRepository;
import com.fhlbsfo.fta.entity.Account;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(AccountRepository repository) {

    return args -> {
      log.info("Initializing account " + repository.save(new Account(10298, 1556000.45)));
      log.info("Initializing account " + repository.save(new Account(36145,2000000.78)));
      log.info("Initializing account " + repository.save(new Account(998566, 3456.45)));
      log.info("Initializing account " + repository.save(new Account(35768,67892.78)));
      
    };
  }
}