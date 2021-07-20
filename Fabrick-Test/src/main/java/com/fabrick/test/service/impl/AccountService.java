package com.fabrick.test.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabrick.test.model.Transaction;
import com.fabrick.test.repos.TransactionRepository;
import com.fabrick.test.request.BankTransferRequest;
import com.fabrick.test.response.BalanceStatusResponse;
import com.fabrick.test.response.BankTransferResponse;
import com.fabrick.test.response.TransactionListResponse;
import com.fabrick.test.service.IAccountService;

@Service
public class AccountService implements IAccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private RestTemplate restTemplate;	
	
	// Properties
	
	@Value("${endpoint.account.balance}")
	private String balanceEndpoint; 
	
    @Value("${endpoint.account.transactions}")
	private String transactionsEndpoint; 
	
    @Value("${endpoint.account.banktransfer}")
	private String bankTransferEndpoint; 
    
    
    @Value("${config.persist.transactions}")
	private Boolean persistTransaction;
    
    // Repos
    
    @Autowired
    private TransactionRepository transactionRepo;
    
	@Override
	public Optional<BalanceStatusResponse> getAccountBalance(Long accountId) {
		logger.info("Retieving Balance for AccounId: {}", accountId);
		
		ResponseEntity<BalanceStatusResponse> res = restTemplate.exchange(balanceEndpoint, HttpMethod.GET, new HttpEntity<>(null), BalanceStatusResponse.class, accountId);
		return Optional.ofNullable((BalanceStatusResponse)res.getBody());
	}
	
	@Override
	public Optional<TransactionListResponse> getAccountTransactions(Long accountId, String fromDate, String toDate) {
		logger.info("Retieving Transactions List for AccountId {} for period from {} to {} ", accountId, fromDate, toDate);
				
		String resolvedUri = resolveGetAccountTransactionsUri(fromDate, toDate);
		ResponseEntity<TransactionListResponse> res = restTemplate.exchange(resolvedUri, HttpMethod.GET, new HttpEntity<>(null), TransactionListResponse.class, accountId);
		
		if (persistTransaction)
			saveAllTransactions(res.getBody().getPayload().getList());
		
		return Optional.ofNullable((TransactionListResponse)res.getBody());
	}

	@Override
	public Optional<BankTransferResponse> submitBankTransfer(Long accountId, BankTransferRequest request) {
		logger.info("Submit Bank Transfer request for AccountId: {}", accountId);
		logger.info("REQUEST: {}", request);
		ResponseEntity<BankTransferResponse> res = restTemplate.exchange(bankTransferEndpoint, HttpMethod.POST, new HttpEntity<>(request), BankTransferResponse.class, accountId);
		return Optional.ofNullable((BankTransferResponse)res.getBody());
	}
	
	@Override
	public List<Transaction> saveAllTransactions(List<Transaction> transactions) {
		logger.info("Persisting Account transactions...");
		return transactionRepo.saveAll(transactions);
	}
	
	private String resolveGetAccountTransactionsUri(String fromDate, String toDate) {
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(transactionsEndpoint)
	                .queryParam("fromAccountingDate", fromDate)
	                .queryParam("toAccountingDate", toDate).build();
		return builder.toUriString();
	}
}
