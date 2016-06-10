package org.devgateway.geoph.core.services;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.model.Location;

import java.util.List;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public interface LocationService {

    List<Location> getLocationsForExport(Parameters params);

}
