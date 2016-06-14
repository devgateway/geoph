package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.LocationRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.LocationService;
import org.devgateway.geoph.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationRepository locationRepository;

    public List<Location> getLocationsForExport(Parameters params) {
        return locationRepository.findLocationsByParams(params);
    }
}

