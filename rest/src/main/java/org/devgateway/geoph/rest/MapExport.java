package org.devgateway.geoph.rest;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import com.machinepublishers.jbrowserdriver.UserAgent;
import net.anthavio.phanbedder.Phanbedder;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by sebas on 8/3/2016.
 */

@RestController
@RequestMapping(value = "/export")
public class MapExport {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapExport.class);

    public static File phantomjs = Phanbedder.unpack(); //Phanbedder to the rescue!

    //JBrowserDriver
    @RequestMapping(value = "/pdf", method = POST)
    public Map<String, String>  toPdf1(@RequestBody Map<String, String> params) throws Exception {

        Integer width=Integer.parseInt(params.get("width"));
        Integer height=Integer.parseInt(params.get("height"));
        Document doc = Jsoup.parse(new File("//C:/JS_PROJECTS/ph/templates/map.html"), "utf-8");
        doc.getElementById("content").append(params.get("html"));
        doc.getElementById("map1").attr("style", "width:" + width + "px;height:" + height + "px");
        Element pane=doc.getElementsByClass("leaflet-map-pane").get(0);
        String style=pane.attr("style");

        //Jbrowser doesn't support translate3d
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

        pane.attr("style", "left:" + left + ";top:" + top);

        Dimension screen=new Dimension(width, height);
        File tmp = File.createTempFile("geoph-temp-html", ".html");
        System.out.println(tmp.getAbsolutePath());
        FileUtils.writeStringToFile(tmp, doc.outerHtml());
        WebDriver driver = new JBrowserDriver(Settings
                .builder()
                .logWarnings(false)
                .screen(screen)
                .userAgent(UserAgent.CHROME)
                .build());

        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.get(tmp.toURI().toString());

        String base64Image=((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        driver.quit();
        HashMap results=new HashMap();
        results.put("image",base64Image);
        results.put("link","");

        return results;

    }



}
