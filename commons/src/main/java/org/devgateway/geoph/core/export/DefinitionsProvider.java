package org.devgateway.geoph.core.export;

import java.util.List;
import java.util.Map;

/**
 * Created by Sebastian Dimunzio on 6/13/2016.
 */
public interface DefinitionsProvider {

    List<ColumnDefinition> getColumnsDefinitions();

    Map<String, List<String>> getMethodsToInvoke();

}
