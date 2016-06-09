package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.AppMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 20 2016.
 */
public interface AppMapService {

    List<AppMap> findByNameOrDescription(String name);

    AppMap findByKey(String key);

    AppMap save(AppMap appMap);

    AppMap update(long id, AppMap appMap);

    AppMap findById(long id);

    Page<AppMap> findAll(Pageable pageable);

}
