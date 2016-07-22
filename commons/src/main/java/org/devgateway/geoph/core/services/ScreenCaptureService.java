package org.devgateway.geoph.core.services;

/**
 * @author dbianco
 *         created on jun 20 2016.
 */
public interface ScreenCaptureService {

    String captureKeyToImage(String key) throws Exception;

    String captureUrlToImage(String url) throws Exception;

    String captureUrlToPDF(String url);

    String captureKeyToPDF(String key);

    String htmlToPDF(String url);
}
