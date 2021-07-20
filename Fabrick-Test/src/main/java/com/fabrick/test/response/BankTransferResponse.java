package com.fabrick.test.response;

import com.fabrick.test.model.BalanceRecord;

import lombok.Data;

@Data
public class BankTransferResponse extends DefaultResponse {
	
	private BalanceRecord payload;
}
