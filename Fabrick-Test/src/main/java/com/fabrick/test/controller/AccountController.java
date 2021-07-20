package com.fabrick.test.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabrick.test.request.BankTransferRequest;
import com.fabrick.test.response.BalanceStatusResponse;
import com.fabrick.test.response.BankTransferResponse;
import com.fabrick.test.response.TransactionListResponse;
import com.fabrick.test.service.IAccountService;

@RestController
public class AccountController {

	@Autowired
	private IAccountService accountService;
	
	@GetMapping(value = "/api/account/{accountId}/balance")
	public ResponseEntity<BalanceStatusResponse> getSaldoAccount(@PathVariable("accountId") Long accountId) {
		Optional<BalanceStatusResponse> res = accountService.getAccountBalance(accountId);
		return res.isPresent() ? ResponseEntity.ok(res.get()) : ResponseEntity.badRequest().body(null);
	}
	
	@GetMapping(value = "/api/account/{accountId}/transactions")
	public ResponseEntity<TransactionListResponse> getAccountTransactions(@PathVariable("accountId") Long accountId, @RequestParam(required = true) String fromAccountingDate, @RequestParam(required = true) String toAccountingDate) {
		Optional<TransactionListResponse> res = accountService.getAccountTransactions(accountId, fromAccountingDate, toAccountingDate);
		return res.isPresent() ? ResponseEntity.ok(res.get()) : ResponseEntity.badRequest().body(null);
	}
	
	@PostMapping(value = "/api/account/{accountId}/banktransfer")
	public ResponseEntity<BankTransferResponse> getAccountTransactions(@PathVariable("accountId") Long accountId, @RequestBody BankTransferRequest request) {
		Optional<BankTransferResponse> res = accountService.submitBankTransfer(accountId, request);
		return res.isPresent() ? ResponseEntity.ok(res.get()) : ResponseEntity.badRequest().body(null);
	}
}
