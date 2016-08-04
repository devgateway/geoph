package org.devgateway.geoph.services;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.devgateway.geoph.core.services.ScreenCaptureService;
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
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author dbianco
 *         created on jun 20 2016.
 */
@Service
public class ScreenCaptureServiceImpl implements ScreenCaptureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenCaptureServiceImpl.class);
    private static final String PNG_EXTENSION = ".png";
    private static final String PDF_EXTENSION = ".pdf";

    @Value("${screen.capture.pdf.width}")
    private int pdfWidth;

    @Value("${screen.capture.pdf.height}")
    private int pdfHeight;

    @Value("${screen.capture.img.width}")
    private int imgWidth;

    @Value("${screen.capture.img.height}")
    private int imgHeight;

    @Value("${screen.capture.dir}")
    private String storageFolder;

    @Value("${screen.capture.waiting.time}")
    private Long timeToWait;

    @Value("${screen.capture.img.url}")
    private String imgUrl;


    @Override
    public String captureKeyToImage(String key) {
        LOGGER.debug("Capturing image for URL: " + imgUrl + key);

        String filename = key + PNG_EXTENSION;
        try {
            createImageFromUrl(imgUrl + key, filename);
            LOGGER.debug("Image done: " + filename);
        } catch (Exception e) {
            LOGGER.error("Error creating image " + e.getMessage());
            filename = null;

        }
        return filename;
    }

    @Override
    public String captureUrlToImage(String url) {
        LOGGER.debug("Capturing image for URL: " + url);

        String filename = UUID.randomUUID() + PNG_EXTENSION;
        try {
            createImageFromUrl(url, filename);
            LOGGER.debug("Image done: " + filename);
        } catch (Exception e) {
            LOGGER.error("Error creating image " + e.getMessage());
            filename = null;

        }
        return filename;
    }

    @Override
    public String captureKeyToPDF(String key){
        LOGGER.debug("Capturing pdf for key: " + key);

        String name = key;
        String filename = name + PDF_EXTENSION;
        try {
            File imageFile = createImageFromUrl(imgUrl+key, name + PNG_EXTENSION);
            createPDF(filename, imageFile);
            LOGGER.debug("PDF done: " + filename);
        } catch (Exception e) {
            LOGGER.error("Error creating pdf " + e.getMessage());
            filename = null;
        }
        return filename;

    }

    @Override
    public String captureUrlToPDF(String url){
        LOGGER.debug("Capturing pdf for URL: " + url);

        String name = UUID.randomUUID().toString();
        String filename = name + PDF_EXTENSION;
        try {
            File imageFile = createImageFromUrl(url, name + PNG_EXTENSION);
            createPDF(filename, imageFile);
            LOGGER.debug("PDF done: " + filename);
        } catch (Exception e) {
            LOGGER.error("Error creating pdf " + e.getMessage());
            filename = null;
        }
        return filename;

    }

    private File createImageFromUrl(String url, String filename) throws InterruptedException, IOException {
        WebDriver driver = new JBrowserDriver(Settings
                .builder()
                .logWarnings(false)
                .logger(null)
                .timezone(Timezone.AMERICA_NEWYORK)
                .build());
        driver.manage().timeouts().pageLoadTimeout(timeToWait, TimeUnit.SECONDS);
        driver.get(url);
        File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(imageFile, new File(storageFolder+filename));
        driver.quit();
        return imageFile;
    }

    private void createPDF(String fileName, File imageFile) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        BufferedImage image = ImageIO.read(imageFile);
        PDImageXObject  imageObj = LosslessFactory.createFromImage(document, image);
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
        Dimension scaledDim = getAdaptedDimension(imageObj.getWidth(), imageObj.getHeight());

        contentStream.drawImage(imageObj, 0, 0, scaledDim.width, scaledDim.height);
        contentStream.close();
        document.save(storageFolder + fileName);
        document.close();

    }

    private Dimension getAdaptedDimension(final int imgWidth, final int imgHeight) {
        int newWidth = imgWidth;
        int newHeight = imgHeight;

        if (newWidth > pdfWidth) {
            newWidth = pdfWidth;
            newHeight = (newWidth * imgHeight) / imgWidth;
        }

        if (newHeight > pdfHeight) {
            newHeight = pdfHeight;
            newWidth = (newHeight * imgWidth) / imgHeight;
        }

        return new Dimension(newWidth, newHeight);
    }

    public static void main(String[] args){
        try {
            File file = new File("/tmp/geoph.pdf");
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
            PDPageContentStream pc2 = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            pc2.beginText();
            pc2.setFont(PDType1Font.HELVETICA_BOLD, 10);
            pc2.setNonStrokingColor(0,0,0);
            pc2.newLineAtOffset(36, 680);
            pc2.setLeading(15D);
            pc2.showText("http://geoph.developmentgateway.org/#/");

            pc2.endText();
            pc2.close();

            //Image
            BufferedImage image = ImageIO.read(new File("/tmp/test.png"));
            PDImageXObject  imageObj = LosslessFactory.createFromImage(document, image);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
            Dimension scaledDim = getAdaptedDimension2(imageObj.getWidth(), imageObj.getHeight());

            contentStream.drawImage(imageObj, 36, 295, scaledDim.width, scaledDim.height);
            contentStream.close();


            document.save("/tmp/test2.pdf");
            document.close();
        } catch (IOException e) {
            LOGGER.error("Error at: " + e.getMessage());
        }
    }

    private static Dimension getAdaptedDimension2(final int imgWidth, final int imgHeight) {
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
