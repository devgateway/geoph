package org.devgateway.geoph.core.services;

import org.devgateway.geoph.core.request.IndicatorRequest;
import org.devgateway.geoph.core.response.IndicatorResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dbianco
 *         created on jun 17 2016.
 */
public interface ImportService {

    IndicatorResponse importIndicatorFromFile(IndicatorRequest indicatorParam, MultipartFile file);

}
