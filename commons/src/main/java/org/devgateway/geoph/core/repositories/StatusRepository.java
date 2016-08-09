package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("select a from Status a where a.name = ?1")
    Status findByName(String name);

    @Query("select a from Status a where a.code = ?1")
    Status findByCode(String code);
<<<<<<< .merge_file_a99852
=======

    @Query("select a from Status a where a.id = ?1")
    Status findById(Long id);
>>>>>>> .merge_file_a87956
}
