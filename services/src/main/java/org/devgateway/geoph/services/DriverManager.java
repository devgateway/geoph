package org.devgateway.geoph.services;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import com.machinepublishers.jbrowserdriver.UserAgent;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by sebas on 9/12/2016.
 */
public class DriverManager {

    private static HashMap<Integer, HashMap<Integer, WebDriver>> drivers = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);

    private static WebDriver createDriver(Integer width, Integer height) {
        LOGGER.debug("Starting new JBrowserDriver ("+width+"px, "+height+"px)");
        Dimension screen = new Dimension(width, height);
        WebDriver driver = new JBrowserDriver(Settings
                .builder()
                .logWarnings(false)
                .logger(null)
                .screen(screen)
                .userAgent(UserAgent.CHROME)
                .timezone(Timezone.AMERICA_NEWYORK)
                .build());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        LOGGER.debug("Web driver started");
        return driver;
    }

    static WebDriver getDriver(Integer width, Integer height) {

        if (!drivers.containsKey(width)) {
            //no existing width create the full mapping
            HashMap<Integer, WebDriver> map = new HashMap<>();
            map.put(height, createDriver(width, height));
            drivers.put(width, map);
        } else {
            if (!drivers.get(width).containsKey(height)) {
                //no existing height create a new one
                HashMap<Integer, WebDriver> map = new HashMap<>();
                map.put(height, createDriver(width, height));
                drivers.put(width, map);
            }
        }
        return drivers.get(width).get(height);
    }
}
