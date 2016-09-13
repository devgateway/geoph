package org.devgateway.geoph.core.services;

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

    void delete(long id);

    void deleteByKey(String key);

    Page<AppMap> findAll(Pageable pageable);

    Page<AppMap> findByType(String type,Pageable pageable);

    AppMap findByName(String mapName);

    AppMap findByMD5(String md5);
}
