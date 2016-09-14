package org.devgateway.geoph.services;

import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.core.repositories.AppMapRepository;
import org.devgateway.geoph.core.services.AppMapService;
import org.devgateway.geoph.model.AppMap;
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
    public List<AppMap> findByNameOrDescription(String name) {
        if (StringUtils.isNotBlank(name)) {
            return repository.findByNameOrDescription(name.toLowerCase());
        }
        return null;
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
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public void deleteByKey(String key) {
        AppMap map = repository.findByKey(key);
        if(map!=null){
            repository.delete(map.getId());
        }
    }

    @Override
    public Page<AppMap> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<AppMap> findByType(String type,Pageable pageable) {
        return repository.findByType(type,pageable);
    }

    @Override
    public AppMap findByName(String mapName){
        return repository.findByName(mapName);
    }

    @Override
    public AppMap findByMD5(String md5){
        return repository.findByMD5(md5);
    }
}
