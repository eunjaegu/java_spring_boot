package com.ej.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ej.api.dto.CsvDataDto;
import com.ej.api.entity.Transaction;


public interface TransactionRepository  extends JpaRepository<Transaction, Long>{
	@Query("SELECT  c.transactionDate, c.merchantName, c.amount from Transaction c")
	
	List<Object[]> findTransactionDetails();

}
