package org.devgateway.geoph.deployer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Sebastian Dimunzio on 2/17/2016.
 */
@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(StaticResourceConfiguration.class);

    @Value("${static.path}")
    private String staticPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        if(staticPath != null) {
            LOG.info("Serving static content from " + staticPath);
            registry.addResourceHandler("/**").addResourceLocations("file:" + staticPath);
        }
    }

    // see https://stackoverflow.com/questions/27381781/java-spring-boot-how-to-map-my-my-app-root-to-index-html
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/index.html");
    }

}
