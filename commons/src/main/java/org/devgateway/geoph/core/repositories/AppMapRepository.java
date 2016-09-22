package org.devgateway.geoph.core.repositories;

import org.devgateway.geoph.model.AppMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("select a from AppMap a where LOWER(a.name) LIKE LOWER(CONCAT('%',?1, '%'))")
    List<AppMap> findByName(String name);

    @Query("select a from AppMap a where a.key = ?1")
    AppMap findByKey(String key);

    @Query("select a from AppMap a where a.md5 = ?1")
    AppMap findByMD5(String md5);

    @Query("select a from AppMap a  where a.type= :type")
    Page<AppMap> findByType(@Param("type") String type, Pageable pageable);

}
