package com.payMyBuddy.service;

import com.payMyBuddy.model.Transaction;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
	List<Transaction> getAllTransactions();
	Transaction transferMoney(@NonNull UUID id, @NonNull double amount, @NonNull String description);
	Transaction transferToBank(String iban, double amount, String description);
	void addMoney(double amount);

}
