package org.devgateway.geoph.core.services;

import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.request.Parameters;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public interface ExportService {

    String export(Generator generator, Parameters parameters) throws Exception;


}
