package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
