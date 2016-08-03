package org.devgateway.geoph.rest;

import com.sun.xml.internal.ws.policy.AssertionSet;
import net.anthavio.phanbedder.Phanbedder;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.StringReader;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by sebas on 8/3/2016.
 */

@RestController
@RequestMapping(value = "/export")
public class MapExport {

  public static File phantomjs = Phanbedder.unpack(); //Phanbedder to the rescue!

    @RequestMapping(value = "/pdf", method = POST)
    public String toPdf(@RequestBody Map<String, Object> params) throws Exception {
        File template=new File("file:///C:/JS_PROJECTS/ph/templates/map.html");
        FileRe

        File phantomjs = Phanbedder.unpack();
        DesiredCapabilities dcaps = new DesiredCapabilities();
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjs.getAbsolutePath());
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.get(");
                driver.executeScript("document.getElementById(\"root\").innerHTML=" + params.get("html"));
        //WebElement query = driver.findElement(By.name("q"));
        //query.sendKeys("Phanbedder");
        //query.submit();

       System.out.print(driver.getScreenshotAs(OutputType.FILE).getAbsolutePath());
        driver.quit();
        return null;

    }

}
