package com.payMyBuddy.service;

import com.payMyBuddy.model.Transaction;
import com.sun.istack.NotNull;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
	List<Transaction> getAllTransactions();
	Transaction transferMoney(@NotNull UUID id, @NotNull double amount, @NotNull String description);

}
