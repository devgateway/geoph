package org.devgateway.geoph.core.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dbianco
 *         created on ago 08 2016.
 */
public interface PrintService {

    Map<String, Set<String>> getFilterNamesFromJson(Map jsonFilters);

    List<String> getLayerNamesFromJson(List jsonLayers);

}
