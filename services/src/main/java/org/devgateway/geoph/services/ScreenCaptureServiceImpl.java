package org.devgateway.geoph.services;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import com.machinepublishers.jbrowserdriver.UserAgent;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
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

    @Value("${screen.capture.templates.html}")
    private String htmlTemplate;

    @Value("${screen.capture.templates.pdf}")
    private String pdfTemplate;

    @Value("${screen.capture.folder}")
    private String storageFolder;

    @Value("${screen.capture.waiting.time}")
    private Long timeToWait;

    @Override
    public String createPdfFromHtmlString(Integer width, Integer height, String html){
        String pdfFilename = null;
        String name = UUID.randomUUID().toString();

        File tmpHtml = getTempHtmlFile(name, width, height, html);
        LOGGER.debug("tmpHtml created");

        if(tmpHtml != null){
            String imageFilename = createImageFromFile(name, width, height, tmpHtml);
            LOGGER.debug("image file created");
            if(imageFilename != null){
                pdfFilename = createPdf(name, imageFilename);
                LOGGER.debug("pdf file created");
            }
        }
        return pdfFilename;
    }

    private String createImageFromFile(String name, Integer width, Integer height, File tmp) {
        String filename = null;
        try {
            Dimension screen = new Dimension(width, height);
            WebDriver driver = new JBrowserDriver(Settings
                    .builder()
                    .logWarnings(false)
                    .logger(null)
                    .screen(screen)
                    .userAgent(UserAgent.CHROME)
                    .timezone(Timezone.AMERICA_NEWYORK)
                    .build());

            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            driver.get(tmp.toURI().toString());

            File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            filename = storageFolder + name + PNG_EXTENSION;
            FileUtils.copyFile(imageFile, new File(filename));
            driver.quit();
        } catch (Exception e){
            LOGGER.error("Image error: " + e.getMessage());
            filename = null;
        }
        return filename;
    }

    private File getTempHtmlFile(String name, Integer width, Integer height, String html) {
        File file = null;
        try {
            Document doc = Jsoup.parse(new File(htmlTemplate), "utf-8");
            doc.getElementById("content").append(html);
            doc.getElementById("map1").attr("style", "width:" + width + "px;height:" + height + "px");

            //Fix translate3D element
            removeTranslate3dFromDocument(doc);

            file = File.createTempFile(name, HTML_EXTENSION);
            //System.out.println(file.getAbsolutePath());
            FileUtils.writeStringToFile(file, doc.outerHtml());
        } catch (Exception e){
            LOGGER.error("File error: " + e.getMessage());
            file = null;
        }
        return file;
    }

    private void removeTranslate3dFromDocument(Document doc) {
        Element pane=doc.getElementsByClass("leaflet-map-pane").get(0);
        String style=pane.attr("style");
        String pattern = "[-|\\d]*.px";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(style);
        String left;
        if (m.find());{
            left=m.group(0);
        }
        String top;
        if (m.find());{
            top=m.group(0);
        }
        pane.attr("style", "left:" + left + ";top:"+top);
    }

    private String createPdf(String name, String imageFilename){
        String filename = null;
        try {
            File file = new File(pdfTemplate);
            PDDocument document = PDDocument.load(file);
            PDPageTree pages = document.getDocumentCatalog().getPages();
            PDPage page = pages.get(0);

            //Map title
            PDPageContentStream pc = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            pc.beginText();
            pc.setFont(PDType1Font.HELVETICA_BOLD, 13);
            pc.setNonStrokingColor(2, 64, 114);
            pc.newLineAtOffset(36, 695);
            pc.setLeading(15D);
            pc.showText("Title: This is the map title");
            pc.endText();
            pc.close();

            //URL
            pc = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            pc.beginText();
            pc.setFont(PDType1Font.HELVETICA, 10);
            pc.setNonStrokingColor(0,0,0);
            pc.newLineAtOffset(36, 680);
            pc.setLeading(15D);
            pc.showText("http://geoph.developmentgateway.org/#/");

            pc.endText();
            pc.close();

            //Image
            BufferedImage image = ImageIO.read(new File(imageFilename));
            PDImageXObject  imageObj = LosslessFactory.createFromImage(document, image);
            pc = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
            Dimension scaledDim = getAdaptedDimension(imageObj.getWidth(), imageObj.getHeight());

            pc.drawImage(imageObj, 36, 660-scaledDim.height, scaledDim.width, scaledDim.height);
            pc.close();

            //Applied Layers
            pc = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            pc.beginText();
            pc.setFont(PDType1Font.HELVETICA_BOLD, 10);
            pc.setNonStrokingColor(2, 64, 114);
            pc.newLineAtOffset(36, 640-scaledDim.height);
            pc.setLeading(15D);
            pc.showText("Applied Layers");
            pc.endText();
            pc.close();

            //Filter Options
            pc = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            pc.beginText();
            pc.setFont(PDType1Font.HELVETICA_BOLD, 10);
            pc.setNonStrokingColor(2, 64, 114);
            pc.newLineAtOffset(310, 640-scaledDim.height);
            pc.setLeading(15D);
            pc.showText("Filter Options");
            pc.endText();
            pc.close();

            filename = storageFolder + name + PDF_EXTENSION;
            document.save(filename);
            document.close();
        } catch (IOException e) {
            LOGGER.error("Error at: " + e.getMessage());
            filename = null;
        }
        return filename;
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
