package com.payMyBuddy.dao;

import com.payMyBuddy.model.Transaction;
import com.payMyBuddy.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
	
	List<Transaction> findAllByCreditorIdOrDebtorIdOrderByCreationDate(UUID creditorId, UUID debtorId);

}
