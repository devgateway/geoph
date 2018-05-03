package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.model.Pdp;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dbianco on 02/05/2018.
 */
public interface PdpRepository extends JpaRepository<Pdp, Long> {
}
