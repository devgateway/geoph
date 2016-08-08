package org.devgateway.geoph.core.services;


import org.devgateway.geoph.core.request.PrintParams;

/**
 * @author dbianco
 *         created on jun 20 2016.
 */
public interface ScreenCaptureService {

    String createPdfFromHtmlString(PrintParams params, String key) throws Exception;

}
