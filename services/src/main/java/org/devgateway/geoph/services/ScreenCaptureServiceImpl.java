package org.devgateway.geoph.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.devgateway.geoph.core.services.ScreenCaptureService;
import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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

    @Value("${screen.capture.firefox.binary}")
    private String firefoxBinary;

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

    @Override
    public String htmlToPDF(String url){
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
            contentStream.beginText();
            contentStream.setFont( PDType1Font.HELVETICA_BOLD, 12 );
            contentStream.newLineAtOffset(30, 750);
            contentStream.setLeading(15D);
            contentStream.showText("Title: ");
            contentStream.setFont( PDType1Font.HELVETICA, 12 );
            Document doc = Jsoup.connect(url).timeout(13000).get();
            HtmlToPlainText formatter = new HtmlToPlainText();
            Elements elements = doc.select("article"); // get each element that matches the CSS selector
            for (Element element : elements) {
                String[] plainText = formatter.getPlainText(element).split("\n"); // format that element to plain text
                for(String text:plainText){
                    try{
                        contentStream.newLine();
                        contentStream.showText(text);
                    } catch (Exception e){
                        //TODO
                    }
                }
            }
            contentStream.endText();
            contentStream.close();
            document.save(storageFolder + "test.pdf");
            document.close();
        } catch (IOException e) {
            LOGGER.error("Error at: " + e.getMessage());
        }
        return null;
    }

    private File createImageFromUrl(String url, String filename) throws InterruptedException, IOException {
        File imageFile = null;
        if (StringUtils.isNotBlank(firefoxBinary)) {
            File pathToBinary = new File(firefoxBinary);
            FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
            driver.manage().window().setSize(new Dimension(imgWidth, imgHeight));
            driver.get(url);
            Thread.sleep(timeToWait);

            imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(imageFile, new File(storageFolder + filename));
            driver.close();
        }
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
}
