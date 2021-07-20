package com.fabrick.test.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BalanceRecord extends AbstractModel {

	  private String date;
	  private BigDecimal balance;
	  private BigDecimal availableBalance;
	  private String currency; 
}
