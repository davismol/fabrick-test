package com.fabrick.test.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Transactions")
public class Transaction extends AbstractModel {

	private String transactionId;
	private String operationId;
}
