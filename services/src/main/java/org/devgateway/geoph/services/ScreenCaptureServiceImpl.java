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
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.util.Base64;
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

    @Value("${screen.capture.waiting.time}")
    private Long timeToWait;


    @Value("#{environment['repository.path']}")
    String respository;



    public String createPdfFromHtmlString(Integer width, Integer height, String html) throws Exception {
        File target = buildPage(width, height, html); //merge template and the passed html and return URL to resulted file
        BufferedImage image = captureImage(width, height, target.toURI()); //create screen shoot from html file
       if (image==null){
           throw  new Exception("Wasn't able to generate image please check logs");
       }
        return createPdf(image).getName();
    }





    public BufferedImage captureImage(Integer width, Integer height, URI target) {
        LOGGER.error("Starting JBrowserDriver ");
        BufferedImage image = null;
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
    //TODO:externalize time out
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
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

    @Override
    /**
     * Scale image keeping aspect ration
     */
    public BufferedImage scaleWidth(BufferedImage original, Integer newWidth) {
        Integer w=original.getWidth();
        Float ratio=((float)w)/newWidth;
        Float   newHeight=original.getHeight()/ratio;
        return  resize(original, newWidth, newHeight.intValue());
    }

    @Override
    /**
     * Scale image keeping aspect ration
     */
    public BufferedImage scaleHeight(BufferedImage original, Integer newHeight) {
        Integer h=original.getHeight();
        Integer ratio=h/newHeight;
        Integer  newWidth=original.getWidth()/ratio;
        return  resize(original, newWidth, newHeight);
    }

    private  BufferedImage resize(BufferedImage original, Integer width, Integer height) {
        BufferedImage scaledBI = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledBI.createGraphics();
        g.drawImage(original, 0, 0, width, height, null);
        g.dispose();
        return scaledBI;
    }



    public File buildPage(Integer width, Integer height, String html) {
        LOGGER.error("Merge html");
        File file = null;
        try {
            Document doc = Jsoup.parse(new File(htmlTemplate), "utf-8");
            doc.getElementById("content").append(html);
            doc.getElementById("map1").attr("style", "width:" + width + "px;height:" + height + "px");

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

    @Override
    public String toBase64(BufferedImage image) throws IOException {
        BASE64Encoder base64Encoder=new BASE64Encoder();
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        byte[] imageBytes = bos.toByteArray();

        BASE64Encoder encoder = new BASE64Encoder();
        imageString = encoder.encode(imageBytes);

        bos.close();
        return imageString;
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

    private File createPdf(BufferedImage image) {
        LOGGER.error("CreatePdf");
        String name = UUID.randomUUID().toString();
        File pdfFile=new File(respository,name + PDF_EXTENSION);

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
            pc.setNonStrokingColor(0, 0, 0);
            pc.newLineAtOffset(36, 680);
            pc.setLeading(15D);
            pc.showText("http://geoph.developmentgateway.org/#/");

            pc.endText();
            pc.close();

            //Image

            PDImageXObject imageObj = LosslessFactory.createFromImage(document, image);
                pc = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
            Dimension scaledDim = getAdaptedDimension(imageObj.getWidth(), imageObj.getHeight());

            pc.drawImage(imageObj, 36, 660 - scaledDim.height, scaledDim.width, scaledDim.height);
            pc.close();

            //Applied Layers
            pc = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            pc.beginText();
            pc.setFont(PDType1Font.HELVETICA_BOLD, 10);
            pc.setNonStrokingColor(2, 64, 114);
            pc.newLineAtOffset(36, 640 - scaledDim.height);
            pc.setLeading(15D);
            pc.showText("Applied Layers");
            pc.endText();
            pc.close();

            //Filter Options
            pc = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            pc.beginText();
            pc.setFont(PDType1Font.HELVETICA_BOLD, 10);
            pc.setNonStrokingColor(2, 64, 114);
            pc.newLineAtOffset(310, 640 - scaledDim.height);
            pc.setLeading(15D);
            pc.showText("Filter Options");
            pc.endText();
            pc.close();
            document.save(pdfFile);
            document.close();
        } catch (IOException e) {
            LOGGER.error("Error at: " + e.getMessage());

        }
        return pdfFile;

    }
    //TODO: does this function will keep aspect ration?
    //TODO:externalize max sizes
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
