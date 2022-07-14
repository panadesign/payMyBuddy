package com.payMyBuddy.service;

import com.payMyBuddy.model.Transaction;
import com.sun.istack.NotNull;

import java.util.List;

public interface TransactionService {
	List<Transaction> getAllTransactions();
	Transaction transferMoney(@NotNull String email, @NotNull double amount, @NotNull String description);

}
