package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("select a from Location a where a.code = ?1")
    Location findByCode(String code);

    @Query("select a from Location a where a.type = ?1")
    List<Location> findLocationsByType(int type);

    @Query(nativeQuery = true, value = "Select l.* from location l inner join location_items li on l.id=li.items_id where li.location_id = ?1")
    List<Location> findLocationsByParentId(long parentId);

}
