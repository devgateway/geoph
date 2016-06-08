package org.devgateway.geoph.services.repository;

import org.devgateway.geoph.model.AppMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 20 2016.
 */
@Transactional
public interface AppMapRepository extends JpaRepository<AppMap, Long> {

    @Query("select a from AppMap a where LOWER(a.name) LIKE LOWER(CONCAT('%',?1, '%')) OR LOWER(a.description) LIKE LOWER(CONCAT('%',?1, '%'))")
    List<AppMap> findByNameOrDescription(String name);

    @Query("select a from AppMap a where a.key = ?1")
    AppMap findByKey(String key);

}
