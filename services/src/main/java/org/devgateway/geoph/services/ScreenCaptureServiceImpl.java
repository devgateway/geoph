package org.devgateway.geoph.services;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import com.machinepublishers.jbrowserdriver.UserAgent;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.devgateway.geoph.core.request.PrintParams;
import org.devgateway.geoph.core.services.ScreenCaptureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dbianco
 *         created on jun 20 2016.
 */
@Service
public class ScreenCaptureServiceImpl implements ScreenCaptureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenCaptureServiceImpl.class);
    private static final String PNG_EXTENSION = ".png";
    private static final String PDF_EXTENSION = ".pdf";
    private static final String HTML_EXTENSION = ".html";
    private static final Color BLUE = new Color(2, 64, 114);
    private static final Color BLACK = new Color(0, 0, 0);

    @Value("${screen.capture.templates.html}")
    private String htmlTemplate;

    @Value("${screen.capture.templates.pdf}")
    private String pdfTemplate;

    @Value("${screen.capture.waiting.time}")
    private Long timeToWait;

    @Value("${screen.capture.img.url}")
    private String urlToShare;

    @Value("#{environment['repository.path']}")
    private String repository;


    public String createPdfFromHtmlString(PrintParams params, String key) throws Exception {
        File target = mergeHtml(params); //merge template and the passed html and return URL to resulted file
        BufferedImage image = captureImage(params, target.toURI()); //create screen shoot from html file
        if(image==null){
           throw  new Exception("Wasn't able to generate image please check logs");
        }
        return createPdf(image, params, key).getName();
    }

    private BufferedImage captureImage(PrintParams params, URI target) {
        LOGGER.debug("Starting JBrowserDriver ");
        BufferedImage image = null;
        try {
            Dimension screen = new Dimension(params.getWidth(), params.getHeight());
            WebDriver driver = new JBrowserDriver(Settings
                    .builder()
                    .logWarnings(false)
                    .logger(null)
                    .screen(screen)
                    .userAgent(UserAgent.CHROME)
                    .timezone(Timezone.AMERICA_NEWYORK)
                    .build());

            driver.manage().timeouts().pageLoadTimeout(timeToWait, TimeUnit.SECONDS);
            driver.get(target.toString());


            byte[] imageByte=((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            driver.quit();
        } catch (Exception e) {
            LOGGER.error("Image error: " + e.getMessage());
        }
        return image;
    }

    private File mergeHtml(PrintParams params) {
        LOGGER.debug("Merge html");
        File file = null;
        try {
            Document doc = Jsoup.parse(readResourceFromContext(htmlTemplate), "utf-8");
            doc.getElementById("content").append(params.getHtml());
            doc.getElementById("map1").attr("style", "width:" + params.getWidth() + "px;height:" + params.getHeight() + "px");

            //Fix translate3D element
            removeTranslate3dFromDocument(doc);

            file = File.createTempFile("map-print", HTML_EXTENSION);
            //System.out.println(file.getAbsolutePath());
            FileUtils.writeStringToFile(file, doc.outerHtml());
        } catch (Exception e) {
            LOGGER.error("File error: " + e.getMessage());

        }
        return file;
    }

    private File readResourceFromContext(String resource){
        File file = null;
        URL res = getClass().getClassLoader().getResource(resource);
        LOGGER.debug(res.toString());
        if (res.toString().startsWith("jar:")) {
            try {
                LOGGER.debug("getting file from jar " + resource);
                InputStream input = getClass().getResourceAsStream(resource);

                file = File.createTempFile("tempfile", ".tmp");
                OutputStream out = new FileOutputStream(file);
                int read;
                byte[] bytes = new byte[1024];
                while ((read = input.read(bytes)) != -1) {
                    LOGGER.debug("reading input...");
                    out.write(bytes, 0, read);
                }
                LOGGER.debug("reading finished");
                file.deleteOnExit();
                LOGGER.debug("file created from Jar file");
            } catch (IOException ex) {
                LOGGER.error("Error on reading resource: " + ex.getMessage());
            }
        } else {
            file = new File(res.getFile());
            LOGGER.debug("file created from Classloader");
        }
        return file;
    }

    /**
     * Due to webkit compatibility translate3d should be removed
     *
     * @param doc
     */
    private void removeTranslate3dFromDocument(Document doc) {
        Element pane = doc.getElementsByClass("leaflet-map-pane").get(0);
        String style = pane.attr("style");
        String pattern = "[-|\\d]*.px";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(style);
        String left;
        if (m.find()) ;
        {
            left = m.group(0);
        }
        String top;
        if (m.find()) ;
        {
            top = m.group(0);
        }
        pane.attr("style", "left:" + left + ";top:" + top);
    }

    private File createPdf(BufferedImage image, PrintParams params, String key) {
        LOGGER.debug("CreatePdf");
        File pdfFile = new File(repository, key + PDF_EXTENSION);

        try {
            File file = readResourceFromContext(pdfTemplate);
            PDDocument document = PDDocument.load(file);
            PDPageTree pages = document.getDocumentCatalog().getPages();
            PDPage page = pages.get(0);
            PDPageContentStream pc;
            int xPos = 36;
            int yPos = 695;

            //Map title
            if(StringUtils.isNotBlank(params.getName())) {
                addPdfText(document, page, xPos, yPos, PDType1Font.HELVETICA_BOLD, 13, BLUE, params.getName());
                yPos -= 15;
            }

            //URL
            if(StringUtils.isNotBlank(key)) {
                addPdfText(document, page, xPos, yPos, PDType1Font.HELVETICA, 10, BLACK, urlToShare + key);
                yPos -= 15;
            }

            //Image
            PDImageXObject imageObj = LosslessFactory.createFromImage(document, image);
                pc = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
            Dimension scaledDim = getAdaptedDimension(imageObj.getWidth(), imageObj.getHeight());
            yPos -= scaledDim.height;
            pc.drawImage(imageObj, xPos, yPos, scaledDim.width, scaledDim.height);
            pc.close();
            yPos -= 20;

            //Applied Layers
            addPdfText(document, page, xPos, yPos, PDType1Font.HELVETICA, 10, BLUE, "Applied Layers");
            yPos -= 20;

            //Filter Options
            Map<String, Map<String, String>> filterMap = getFilterNames(params.getData());
            if(filterMap!= null) {
                addPdfText(document, page, xPos, yPos, PDType1Font.HELVETICA, 10, BLUE, "Filter Options");
                yPos -= 20;
            }

            document.save(pdfFile);
            document.close();
        } catch (IOException e) {
            LOGGER.error("Error at: " + e.getMessage());

        }
        return pdfFile;

    }

    private Map<String, Map<String, String>> getFilterNames(Object data) {
        Map<String, Map<String, String>> ret = null;
        if(data!=null){
            try {
                Map filters = (Map) ((Map) data).get("filters");
                for(Object filterObj:filters.keySet()){
                    String filterStr = (String) filterObj;
                    if(filterStr.equals("ia")){
                        //ret.put
                    }
                }
            } catch (Exception e){

            }

        }

        return ret;
    }

    private void addPdfText(PDDocument document, PDPage page, int xPos, int yPos, PDType1Font font, int fontSize, Color color, String text) throws IOException {
        PDPageContentStream pc;
        pc = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
        pc.beginText();
        pc.setFont(font, fontSize);
        pc.setNonStrokingColor(color);
        pc.newLineAtOffset(xPos, yPos);
        pc.setLeading(15D);
        pc.showText(text);
        pc.endText();
        pc.close();
    }

    private Dimension getAdaptedDimension(final int imgWidth, final int imgHeight) {
        int newWidth = imgWidth;
        int newHeight = imgHeight;

        if (newWidth > 540) {
            newWidth = 540;
            newHeight = (newWidth * imgHeight) / imgWidth;
        }

        if (newHeight > 560) {
            newHeight = 560;
            newWidth = (newHeight * imgWidth) / imgHeight;
        }

        return new Dimension(newWidth, newHeight);
    }


}
