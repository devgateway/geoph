package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}
