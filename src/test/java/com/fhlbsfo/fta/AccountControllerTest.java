package com.fhlbsfo.fta;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fhlbsfo.fta.entity.Account;
import com.fhlbsfo.fta.entity.FundsTransaction;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
	 

	  @Autowired
	  private MockMvc mvc;
	  
	  @Test
	  @Order(1)
	  public void getAllAccounts() throws Exception 
	  {
	    mvc.perform(MockMvcRequestBuilders
	    			.get("/accounts")
	    			.accept(MediaType.APPLICATION_JSON))
	        .andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.[*].accountId").isNotEmpty());
	  }

	  @Test
	  @Order(2)
	  public void createAccount() throws Exception 
	  {
	    mvc.perform( MockMvcRequestBuilders
	  	      .post("/accounts")
	  	      .content(asJsonString(new Account(12098, 345.67)))
	  	      .contentType(MediaType.APPLICATION_JSON)
	  	      .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").exists());
	  }
	  
	  
	  @Test
	  @Order(3)
	  public void deleteAccount() throws Exception 
	  {
	    mvc.perform( MockMvcRequestBuilders
	  	      .delete("/accounts/10298"))
	        .andExpect(status().isOk());
	  }
	  

	  
	  
	  @Test
	  @Order(4)
	  public void transferFunds() throws Exception 
	  {
	    mvc.perform( MockMvcRequestBuilders
	  	      .put("/transferFunds")
	  	      .content(asJsonString(new FundsTransaction(10298, 36145, 234.45)))
	  	      .contentType(MediaType.APPLICATION_JSON)
	  	      .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());
	  }
	  
	  
	  
	  //Tests to validate funds transfer amounts
	  
	  @Test
	  @Order(5)
	  public void createFromAccount() throws Exception 
	  {
	    mvc.perform( MockMvcRequestBuilders
	  	      .post("/accounts")
	  	      .content(asJsonString(new Account(12345, 133.25)))
	  	      .contentType(MediaType.APPLICATION_JSON)
	  	      .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").exists());
	  }
	  
	  @Test
	  @Order(6)
	  public void createToAccount() throws Exception 
	  {
	    mvc.perform( MockMvcRequestBuilders
	  	      .post("/accounts")
	  	      .content(asJsonString(new Account(54321, 100)))
	  	      .contentType(MediaType.APPLICATION_JSON)
	  	      .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").exists());
	  }
	  
	  
	  @Test
	  @Order(7)
	  public void transferTestFunds() throws Exception 
	  {
	    mvc.perform( MockMvcRequestBuilders
	  	      .put("/transferFunds")
	  	      .content(asJsonString(new FundsTransaction(12345, 54321, 50)))
	  	      .contentType(MediaType.APPLICATION_JSON)
	  	      .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());
	  }
	  
	  
	  @Test
	  @Order(8)
	  public void geOneAccount() throws Exception 
	  {
	    mvc.perform( MockMvcRequestBuilders
	  	      .get("/accounts/54321"))
	    .andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.balance").exists())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(150));
	  }
	   
	  public static String asJsonString(final Object obj) {
	      try {
	          return new ObjectMapper().writeValueAsString(obj);
	      } catch (Exception e) {
	          throw new RuntimeException(e);
	      }
	  }
	  

	@Test
	void contextLoads() {
	
	
	}

}
