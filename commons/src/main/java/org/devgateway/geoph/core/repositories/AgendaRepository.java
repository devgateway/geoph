package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dbianco on 02/05/2018.
 */
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
