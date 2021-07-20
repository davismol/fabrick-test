package com.fabrick.test.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabrick.test.model.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>{

}
