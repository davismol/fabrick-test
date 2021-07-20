package com.fabrick.test.request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankTransferRequest {

	private Creditor creditor;
	private String executionDate;
	private String description;
	private BigDecimal amount;
	private String currency;

	/*
	private String uri;
	private Boolean isUrgent;
	private Boolean isInstant;
	private String feeType;
	private String feeAccountId;
	private TaxRelief taxRelief; 
	*/
	
}