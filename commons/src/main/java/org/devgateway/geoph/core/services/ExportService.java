package org.devgateway.geoph.core.services;

import org.devgateway.geoph.core.export.DefinitionsProvider;
import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.request.Parameters;

import java.io.File;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public interface ExportService {

    File exportLocationProject(DefinitionsProvider definitionsProvider, Generator generator, Parameters parameters) throws Exception;

    File exportIndicator(DefinitionsProvider definitionsProvider, Generator generator, Long id) throws Exception;

    File exportIndicatorTemplate(DefinitionsProvider definitionsProvider, Generator generator, String level) throws Exception;
}
