package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.LocationRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.services.LocationService;
import org.devgateway.geoph.dao.LocationResultsQueryHelper;
import org.devgateway.geoph.model.Location;
import org.devgateway.geoph.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationRepository locationRepository;

    public List<Location> getLocationsForExport(Parameters params) {
        List<Location> locationList = new ArrayList<>();

        List<LocationResultsQueryHelper> locationResults = locationRepository.findLocationsByParams(params, 0, 0);

        for (LocationResultsQueryHelper locHelper : locationResults) {
            Set<Project> projects = locHelper.getLocation().getProjects();
            for (Project p : projects) {
                p.getTransactions();
            }
            locationList.add(locHelper.getLocation());
        }

        return locationList;
    }


}
