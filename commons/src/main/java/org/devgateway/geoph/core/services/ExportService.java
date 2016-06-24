package org.devgateway.geoph.core.services;

import org.devgateway.geoph.core.export.DefinitionsProvider;
import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.request.Parameters;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public interface ExportService {

    String exportLocationProject(DefinitionsProvider definitionsProvider, Generator generator, Parameters parameters) throws Exception;

    String exportIndicator(DefinitionsProvider definitionsProvider, Generator generator, Long id) throws Exception;
}
