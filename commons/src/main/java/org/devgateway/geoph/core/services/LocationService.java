package org.devgateway.geoph.core.services;

import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.ProjectLocationDao;

import java.util.List;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public interface LocationService {

    List<ProjectLocationDao> getLocationsForExport(Parameters params);

}
