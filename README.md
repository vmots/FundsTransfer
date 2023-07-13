# FundsTransfer
Simple REST API to transfer funds between accounts

### To compile, run tests and build artifact
mvn clean install 

### To run 
java -jar FundsTransfer-1.0.jar

Note this runs on port 8080 

### Usage

### Get all accounts in the database
From REST Client: ```GET http://localhost:8080/accounts```

Using curl: ```$ curl -v http://localhost:8080/accounts ```

### Get one account from database
From REST Client: ```GET http://localhost:8080/accounts/[account_number]```

Using curl: ```$ curl -v http://localhost:8080/accounts/[account_number]```

### Delete one account
From REST Client: ```DELETE http://localhost:8080/accounts/[account_number]```

Using curl: ```$ curl -X DELETE http://localhost:8080/accounts/[account_number]```

### Add/Update Account
From REST Client: ```POST http://localhost:8080/accounts JSON: {"accountId": [account_number], "balance": [balance_amount] }```

Using curl: 
```$ curl -v -X POST http://localhost:8080/accounts -H "Content-Type: application/json" -d '{"accountId":[account_number],"balance":[balance_amount]}'```

#### Transfer Funds between accounts
From REST Client: ```PUT http://localhost:8080/transferFunds JSON: {
"accountFromId": [account_from_number], 
"accountToId": [account_to_number], 
"amount": [transfer_amount]
}```

Using curl: 
```$ curl -v -X PUT http://localhost:8080/transferFunds -H "Content-Type: application/json" -d '{"accountFromId":[account_number],"accountToId":[balance_amount],"amount":[transfer_amount]}'```

