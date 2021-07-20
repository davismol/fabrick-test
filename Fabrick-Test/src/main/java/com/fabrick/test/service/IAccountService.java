package com.fabrick.test.service;

import java.util.List;
import java.util.Optional;

import com.fabrick.test.model.Transaction;
import com.fabrick.test.request.BankTransferRequest;
import com.fabrick.test.response.BalanceStatusResponse;
import com.fabrick.test.response.BankTransferResponse;
import com.fabrick.test.response.TransactionListResponse;

public interface IAccountService {

	Optional<BalanceStatusResponse> getAccountBalance(Long accountId);

	Optional<TransactionListResponse> getAccountTransactions(Long accountId, String fromDate, String toDate);

	Optional<BankTransferResponse> submitBankTransfer(Long accountId, BankTransferRequest request);
	
	List<Transaction> saveAllTransactions(List<Transaction> transactions);

}
