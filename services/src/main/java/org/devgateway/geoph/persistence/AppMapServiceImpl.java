package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.AppMap;
import org.devgateway.geoph.persistence.repository.AppMapRepository;
import org.devgateway.geoph.services.AppMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 20 2016.
 */
@Service
public class AppMapServiceImpl implements AppMapService {

    @Autowired
    AppMapRepository repository;

    @Override
    public List<AppMap> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public AppMap findByKey(String key) {
        return repository.findByKey(key);
    }

    @Override
    public AppMap save(AppMap appMap) {
        return repository.save(appMap);
    }

    @Override
    public AppMap update(long id, AppMap appMap) {
        return null;
    }

    @Override
    public AppMap findById(long id) {
        return repository.findOne(id);
    }

    @Override
    public Page<AppMap> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}
