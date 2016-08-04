package org.devgateway.geoph.rest;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by sebas on 8/4/2016.
 */
public class Main {


    public static void main(String[] args) {
        DesiredCapabilities dcaps = new DesiredCapabilities();
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        Dimension screen=new Dimension(1024, 768);
        driver.manage().window().setSize(screen);
        driver.get("file:///C:/Users/sebas/AppData/Local/Temp/geoph-temp-html6597232856152569988.html");

        System.out.println(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
        driver.quit();
    }

}
