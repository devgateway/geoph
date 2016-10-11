package org.devgateway.geoph.services;


import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import com.machinepublishers.jbrowserdriver.UserAgent;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.devgateway.geoph.core.request.PrintParams;
import org.devgateway.geoph.core.response.ChartResponse;
import org.devgateway.geoph.core.services.ScreenCaptureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.List;
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
    private static final String PDF_EXTENSION = ".pdf";
    private static final String HTML_EXTENSION = ".html";
    private static final Color BLUE = new Color(2, 64, 114);
    private static final Color BLACK = new Color(0, 0, 0);
    private static final double UPPERCASE_FACTOR = 1.3;
    private static final int MAX_CHARS = 134;
    private static final int X_POS = 36;
    private static final int Y_POS = 695;
    private static final int MIN_Y_POS = 30;
    private static final String NEW_ITEM = "- ";
    private static final int TOP_COUNT = 5;
    private static final int SECOND_COLUMN_MARGIN = 290;
    private static final String BLANK_STRING = " ";
    private static final int FUNDING_TEXT_LIMIT = 30;
    private static final int FIRST_COLUMN_WIDTH = 160;
    private static final int Y_NORMAL_SPACE = 15;
    private static final int Y_LARGE_SPACE = 20;
    private static final int Y_LEGEND_SPACE = 50;
    private static final int Y_SMALL_SPACE = 5;
    private static final int IMAGE_MAX_WIDTH = 540;
    private static final int IMAGE_MAX_HEIGHT = 410;
    private static final int ONE_BILLION = 1000000000;
    private static final int ONE_MILLION = 1000000;
    private static final String TAB = "    ";

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

    @Value("${screen.capture.funding.currency}")
    private String currency;


    public String createPdfFromHtmlString(PrintParams params, String key) throws Exception {
        LOGGER.debug("createPdfFromHtmlString");
        File target = buildPage(params.getWidth(), params.getHeight(), params.getHtml()); //merge template and the passed html and return URL to resulted file
        BufferedImage image = captureImage(params.getWidth(), params.getHeight(), target.toURI()); //create screen shoot from html file
        if (image == null) {
            throw new Exception("Wasn't able to generate image please check logs");
        }
        return createPdf(image, params.getName(), params.getFilters(), params.getLayers(), params.getAllChartsData(), params.getTrxType(), params.getTrxStatus(), key).getName();
    }


    private BufferedImage captureWithPhantom(Integer width, Integer height, URI target) {
        LOGGER.debug("Starting PhantomDriver ");
        BufferedImage image = null;
        Dimension screen = new Dimension(width, height);
        DesiredCapabilities DesireCaps = new DesiredCapabilities();
        DesireCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:/JS_PROJECTS/phantomjs-2.1.1-windows/bin/phantomjs.exe");

        try {
            WebDriver driver = new PhantomJSDriver(DesireCaps);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().window().setSize(new Dimension(width, height));
            driver.get(target.toString());

            byte[] imageByte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            driver.quit();
        } catch (Exception e) {
            LOGGER.error("Image error: " + e.getMessage());
        }
        return image;
    }

    public BufferedImage JbrowserCapture(Integer width, Integer height, URI target) {
        LOGGER.debug("Starting JbrowserCapture ");
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
            driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
            driver.get(target.toString());

            byte[] imageByte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            driver.quit();
        } catch (Exception e) {
            LOGGER.error("Image error: " + e.getMessage());
        }
        return image;
    }

    public BufferedImage captureImage(Integer width, Integer height, URI target) {
        return JbrowserCapture(width, height, target);
    }

    @Override
    /**
     * Scale image keeping aspect ration
     */
    public BufferedImage scaleWidth(BufferedImage original, Integer newWidth) {
        Integer w = original.getWidth();
        Float ratio = ((float) w) / newWidth;
        Float newHeight = original.getHeight() / ratio;
        return resize(original, newWidth, newHeight.intValue());
    }

    @Override
    /**
     * Scale image keeping aspect ration
     */
    public BufferedImage scaleHeight(BufferedImage original, Integer newHeight) {
        Integer h = original.getHeight();
        Integer ratio = h / newHeight;
        Integer newWidth = original.getWidth() / ratio;
        return resize(original, newWidth, newHeight);
    }


    private BufferedImage resize(BufferedImage original, Integer width, Integer height) {
        BufferedImage scaledBI = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledBI.createGraphics();
        g.drawImage(original, 0, 0, width, height, null);
        g.dispose();
        return scaledBI;
    }

    @Override
    public String toBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        byte[] imageBytes = bos.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        String imageString = encoder.encode(imageBytes);
        bos.close();
        return imageString;
    }


    public File buildPage(Integer width, Integer height, String html) {
        LOGGER.debug("Merge html");
        File file = null;
        try {
            URL url = new URL(htmlTemplate);
            Document doc = Jsoup.parse(url.openConnection().getInputStream(), "utf-8", url.getPath());

            doc.getElementById("content").append(html);
            doc.getElementsByClass("map").attr("style", "width:" + width + "px;height:" + height + "px");

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


    private void removeTranslate3dFromDocument(Document doc) {
        Element pane = doc.getElementsByClass("leaflet-map-pane").get(0);
        Elements elements = doc.getElementsByAttributeValueContaining("style", "translate3d");

        String pattern = "[-|\\d]*.px";
        Pattern r = Pattern.compile(pattern);

        elements.forEach(element -> {
            String style = element.attr("style").substring(element.attr("style").indexOf("translate3d"));
            Matcher m = r.matcher(style);

            String left = null;
            if (m.find()) {
                left = m.group(0);
            }
            String top = null;
            if (m.find()) {
                top = m.group(0);
            }
            String newStyle = element.attr("style").concat("left:" + left + ";top:" + top);
            //element.attr("style").concat("transform:translate(" + left +","+top+")");
            element.attr("style", newStyle);
        });
    }

    private File createPdf(BufferedImage image,
                           String name,
                           Map<String, Set<String>> filterMap,
                           Map<String, List<Map<String, String>>> layerList,
                           Map<String, Collection<ChartResponse>> chartData,
                           String trxType,
                           String trxStatus,
                           String key) {
        LOGGER.debug("CreatePdf");
        File pdfFile = new File(repository, key + PDF_EXTENSION);

        try {
            PDDocument doc = PDDocument.load(new URL(pdfTemplate).openConnection().getInputStream());
            PDPageTree pages = doc.getDocumentCatalog().getPages();
            PDPage pdpage = pages.get(0);
            PDPageContentStream pc;
            PDFDocument pdf = new PDFDocument(Y_POS, X_POS, pdpage, doc);

            //Map title
            if (StringUtils.isNotBlank(name)) {
                addPdfText(pdf, PDType1Font.HELVETICA_BOLD, 13, BLUE, name);
                pdf.yPos -= Y_NORMAL_SPACE;
            }

            //URL
            if (StringUtils.isNotBlank(key)) {
                addPdfText(pdf, PDType1Font.HELVETICA, 10, BLACK, urlToShare + key);
                pdf.yPos -= Y_NORMAL_SPACE;
            }

            //Image
            PDImageXObject imageObj = LosslessFactory.createFromImage(pdf.document, image);
            pc = new PDPageContentStream(pdf.document, pdf.page, PDPageContentStream.AppendMode.APPEND, false);
            Dimension scaledDim = getAdaptedDimension(imageObj.getWidth(), imageObj.getHeight());
            pdf.yPos -= scaledDim.height;
            pc.drawImage(imageObj, pdf.xPos, pdf.yPos, scaledDim.width, scaledDim.height);
            pc.close();
            checkEndOfPage(pdf, Y_LARGE_SPACE);

            //Top 5 funding
            addPdfText(pdf, PDType1Font.HELVETICA, 10, BLUE, "Top Funding for " + StringUtils.capitalize(trxStatus) + " " + StringUtils.capitalize(trxType));
            checkEndOfPage(pdf, Y_NORMAL_SPACE);
            addCharts(chartData, trxType, trxStatus, pdf);

            //Applied Layers
            if (layerList.size() > 0) {
                addPdfText(pdf, PDType1Font.HELVETICA, 10, BLUE, "Applied Layers");
                for (String layerName : layerList.keySet()) {
                    if(pdf.yPos>Y_LEGEND_SPACE) {
                        checkEndOfPage(pdf, Y_NORMAL_SPACE);
                    } else {
                        checkEndOfPage(pdf, Y_LEGEND_SPACE);
                    }
                    addPdfText(pdf, PDType1Font.HELVETICA, 9, BLACK, NEW_ITEM + layerName);
                    checkEndOfPage(pdf, Y_NORMAL_SPACE);
                    addLegend(layerList.get(layerName), pdf);
                }
            }

            //Filter Options
            if (filterMap != null && filterMap.keySet().size() > 0) {
                checkEndOfPage(pdf, Y_LARGE_SPACE);
                addPdfText(pdf, PDType1Font.HELVETICA, 10, BLUE, "Filter Options");
                checkEndOfPage(pdf, Y_NORMAL_SPACE);

                for (String filter : filterMap.keySet()) {
                    List<String> strList = splitValues(MAX_CHARS, filter, filterMap.get(filter));
                    for (String strToPrint : strList) {
                        addPdfText(pdf, PDType1Font.HELVETICA, 9, BLACK, strToPrint);
                        checkEndOfPage(pdf, Y_NORMAL_SPACE);
                    }
                }
            }

            pdf.document.save(pdfFile);
            pdf.document.close();

        } catch (IOException e) {
            LOGGER.error("Error at: " + e.getMessage());
        }
        return pdfFile;

    }

    private void addLegend(List<Map<String, String>> legendList, PDFDocument pdf) throws IOException {
        PDPageContentStream pc = new PDPageContentStream(pdf.document, pdf.page, PDPageContentStream.AppendMode.APPEND, false);
        float nextY = pdf.yPos;
        float nextX = pdf.xPos;
        for (Map legendMap : legendList) {
            pc.addRect(nextX, nextY, 10, 10);

            LinkedHashMap<String, String> colorMap = (LinkedHashMap) legendMap.get("color");
            Color color = Color.white;
            if (colorMap != null) {
                color = new Color(Integer.parseInt(colorMap.get("r")), Integer.parseInt(colorMap.get("g")), Integer.parseInt(colorMap.get("b")));
            }
            pc.setNonStrokingColor(color);
            pc.fill();
            nextX += 12;
            pc.beginText();
            pc.newLineAtOffset(nextX, nextY);
            pc.setFont(PDType1Font.HELVETICA, 8);
            pc.showText((String) legendMap.get("label"));
            pc.endText();
            nextX += 93;
        }
        pc.close();
    }

    private void addCharts(Map<String, Collection<ChartResponse>> chartData, String trxType,
                           String trxStatus, PDFDocument pdf) throws IOException {
        boolean flag = false;
        int maxRows = 0;
        for (String fundingType : chartData.keySet()) {
            int xPos = pdf.xPos;
            int yPos = pdf.yPos;
            if (flag) {
                yPos += Y_SMALL_SPACE;
                xPos += SECOND_COLUMN_MARGIN;
            } else {
                maxRows = 0;
            }
            addPdfText(xPos, yPos, pdf, PDType1Font.HELVETICA_BOLD, 9, BLACK, fundingType);
            checkEndOfPage(pdf, Y_SMALL_SPACE);
            List<ChartResponse> fundingData = new ArrayList<>(chartData.get(fundingType));
            int size = fundingData.size();
            int rows = 0;
            List<List<String>> data = new ArrayList<>();
            for (int i = 0; i < (size < TOP_COUNT ? size : TOP_COUNT); i++) {
                data.add(Arrays.asList(fundingData.get(i).getName(), getFundingString(fundingData.get(i).getFunding(trxType, trxStatus))));
                rows++;
            }
            PDPageContentStream pc = new PDPageContentStream(pdf.document, pdf.page, PDPageContentStream.AppendMode.APPEND, false);
            if (data.size() > 0) {
                drawTable(pdf.page, pc, yPos, xPos, rows, data);
            } else {
                addPdfText(xPos + Y_SMALL_SPACE, yPos - Y_NORMAL_SPACE, pdf, PDType1Font.HELVETICA, 9, BLACK, "No data");
                rows = 1;
            }
            if (rows > maxRows) {
                maxRows = rows;
            }

            pc.close();
            if (flag) {
                checkEndOfPage(pdf, Y_NORMAL_SPACE * maxRows);
                checkEndOfPage(pdf, Y_LARGE_SPACE);
            }
            flag = !flag;
        }
    }

    private String getFundingString(Double fundingValue) {
        StringBuilder sb = new StringBuilder(currency);
        sb.append(BLANK_STRING);
        if (fundingValue / ONE_BILLION > 1) {
            sb.append(String.format("%.3f", fundingValue / ONE_BILLION) + "B");
        } else if (fundingValue / ONE_MILLION > 1) {
            sb.append(String.format("%.3f", fundingValue / ONE_MILLION) + "M");
        } else {
            sb.append(String.format("%.3f", fundingValue));
        }
        return sb.toString();
    }

    private PDFDocument checkEndOfPage(PDFDocument pdf, Integer y) throws IOException {
        pdf.yPos -= y;
        if (pdf.yPos <= MIN_Y_POS) {
            pdf.page = pdf.getNewPage();
            pdf.document.addPage(pdf.page);
            pdf.yPos = Y_POS;
        }
        return pdf;
    }

    private List<String> splitValues(int maxChars, String title, Set<String> values) {
        List<String> ret = new LinkedList<>();
        StringBuilder sb = new StringBuilder(NEW_ITEM + title + ": ");
        boolean isCommaNeeded = false;
        for (String value : values) {
            if (isCommaNeeded) {
                sb.append(", ");
            } else {
                isCommaNeeded = true;
            }
            int upperCase = countCapitals(sb.toString());
            long helper = sb.length() - upperCase + Math.round(upperCase * UPPERCASE_FACTOR);
            if (helper + value.length() < maxChars) {
                sb.append(value);
            } else {
                ret.add(sb.toString());
                sb = new StringBuilder(TAB + value);
            }
        }
        ret.add(sb.toString());
        return ret;
    }

    private int countCapitals(String s) {
        if (s.length() == 1) {
            return (Character.isUpperCase(s.charAt(0)) ? 1 : 0);
        } else {
            return countCapitals(s.substring(1)) +
                    (Character.isUpperCase(s.charAt(0)) ? 1 : 0);
        }
    }

    private void addPdfText(PDFDocument pdf, PDType1Font font, int fontSize, Color color, String text) throws IOException {
        addPdfText(pdf.xPos, pdf.yPos, pdf, font, fontSize, color, text);
    }


    private void addPdfText(int xPos, int yPos, PDFDocument doc, PDType1Font font, int fontSize, Color color, String text) throws IOException {
        PDPageContentStream pc = new PDPageContentStream(doc.document, doc.page, PDPageContentStream.AppendMode.APPEND, true);
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

        if (newWidth > IMAGE_MAX_WIDTH) {
            newWidth = IMAGE_MAX_WIDTH;
            newHeight = (newWidth * imgHeight) / imgWidth;
        }

        if (newHeight > IMAGE_MAX_HEIGHT) {
            newHeight = IMAGE_MAX_HEIGHT;
            newWidth = (newHeight * imgWidth) / imgHeight;
        }

        return new Dimension(newWidth, newHeight);
    }

    private void drawTable(PDPage page, PDPageContentStream pc,
                           float y, float margin, int rows,
                           List<List<String>> content) throws IOException {
        final float rowHeight = Y_NORMAL_SPACE;
        final float cellMargin = 5f;

        //now add the text
        pc.setFont(PDType1Font.HELVETICA, 9);

        float textx = margin + cellMargin;
        float texty = y - rowHeight;
        for (int i = 0; i < content.size(); i++) {
            for (int j = 0; j < content.get(i).size(); j++) {
                String text = content.get(i).get(j);
                pc.beginText();
                pc.newLineAtOffset(textx, texty);
                pc.showText(text != null && text.length() > FUNDING_TEXT_LIMIT ? text.substring(0, FUNDING_TEXT_LIMIT) + "..." : text);
                pc.endText();
                textx += FIRST_COLUMN_WIDTH;
            }
            texty -= rowHeight;
            textx = margin + cellMargin;
        }
    }

    class PDFDocument {

        int yPos;

        int xPos;

        PDPage page;

        PDPage clonePage;

        PDDocument document;

        PDFDocument(int yPos, int xPos, PDPage page, PDDocument document) {
            this.yPos = yPos;
            this.xPos = xPos;
            this.page = page;
            this.document = document;

            setClonePage(page);
        }

        PDPage getNewPage() {
            PDPage ret = clonePage;
            setClonePage(clonePage);
            return ret;
        }

        private void setClonePage(PDPage page) {
            COSDictionary pageDict = page.getCOSObject();
            COSDictionary newPageDict = new COSDictionary(pageDict);
            newPageDict.removeItem(COSName.ANNOTS);

            clonePage = new PDPage(newPageDict);
        }
    }
}
