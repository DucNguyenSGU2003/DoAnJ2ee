package com.UniCharity.UniCharity.repositories;

import com.UniCharity.UniCharity.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
