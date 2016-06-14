package org.devgateway.geoph.core.services;

import org.devgateway.geoph.core.export.ColumnDefinition;
import org.devgateway.geoph.core.export.Generator;
import org.devgateway.geoph.core.request.Parameters;

import java.util.List;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public interface ExportService {

    String export(List<ColumnDefinition> columnsDef, Generator generator, Parameters parameters) throws Exception;

}
