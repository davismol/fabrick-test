package com.fabrick.test.response;

import lombok.Data;

@Data
public class TransactionListResponse extends DefaultResponse {
	
	private TransactionList payload;
}
