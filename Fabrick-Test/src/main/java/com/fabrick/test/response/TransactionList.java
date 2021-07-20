package com.fabrick.test.response;

import java.util.List;

import com.fabrick.test.model.Transaction;

import lombok.Data;

@Data
public class TransactionList {

	private List<Transaction> list;
}
