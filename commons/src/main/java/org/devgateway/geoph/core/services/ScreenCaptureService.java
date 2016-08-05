package org.devgateway.geoph.core.services;


/**
 * @author dbianco
 *         created on jun 20 2016.
 */
public interface ScreenCaptureService {

    String createPdfFromHtmlString(Integer width, Integer height, String html) throws Exception;

}
