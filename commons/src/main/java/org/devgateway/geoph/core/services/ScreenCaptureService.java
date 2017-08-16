package org.devgateway.geoph.core.services;


import org.devgateway.geoph.core.request.PrintData;
import org.devgateway.geoph.core.request.PrintParams;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * @author dbianco
 *         created on jun 20 2016.
 */
public interface ScreenCaptureService {

    String createPdfFromHtmlString(PrintParams params, List<PrintData> printDataList, String key) throws Exception;

    BufferedImage captureImage(Integer width, Integer height, URI target);

    BufferedImage scaleWidth(BufferedImage original,Integer newWidth);

    BufferedImage scaleHeight(BufferedImage original,Integer newHeight);

    File buildPage(Integer width, Integer height, String html);

    String toBase64(BufferedImage image) throws IOException;
}
