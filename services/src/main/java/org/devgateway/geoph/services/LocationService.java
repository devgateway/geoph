package org.devgateway.geoph.services;

import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.persistence.repository.LocationRepository;
import org.devgateway.geoph.persistence.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    public List<Location> getLocations(){
        return locationRepository.findAll();
    }

}
