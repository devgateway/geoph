package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {

}
