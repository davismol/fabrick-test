package com.fabrick.test.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountTransactionsRequest {

	private String fromAccountingDate;
	private String toAccountingDate;
	
}
