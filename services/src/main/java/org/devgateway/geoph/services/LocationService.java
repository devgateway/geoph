package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;


}
