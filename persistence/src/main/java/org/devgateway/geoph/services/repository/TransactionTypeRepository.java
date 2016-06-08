package org.devgateway.geoph.services.repository;

import org.devgateway.geoph.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {

    @Query("select a from transaction_type a where a.name = ?1")
    TransactionType findByName(String name);
}
