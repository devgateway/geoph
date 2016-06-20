package org.devgateway.geoph.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.devgateway.geoph.core.services.ScreenCaptureService;
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

import java.io.File;
import java.util.UUID;

/**
 * @author dbianco
 *         created on jun 20 2016.
 */
@Service
public class ScreenCaptureServiceImpl implements ScreenCaptureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenCaptureServiceImpl.class);

    @Value("${screen.capture.firefox.binary}")
    private String firefoxBinary;

    @Value("${screen.capture.dir}")
    private String storageFolder;

    @Value("${screen.capture.waiting.time}")
    private Long timeToWait;


    @Override
    public String captureUrlToImage(String url) throws Exception{
        LOGGER.debug("Capturing image for URL: " + url);
        String filename = null;

        if (StringUtils.isNotBlank(firefoxBinary)) {
            File pathToBinary = new File(firefoxBinary);
            FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
            driver.get(url);
            Thread.sleep(timeToWait);
            filename = UUID.randomUUID() + ".png";
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(storageFolder + filename));
            driver.close();
        }

        return filename;
    }
}
