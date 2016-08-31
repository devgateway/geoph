package org.devgateway.geoph.importer.spring;

import com.vividsolutions.jts.geom.GeometryFactory;
import de.micromata.opengis.kml.v_2_2_0.*;
import de.micromata.opengis.kml.v_2_2_0.Document;
import org.devgateway.geoph.core.repositories.GeoPhotoRepository1;
import org.devgateway.geoph.core.repositories.GeoPhotoSourceRepository;
import org.devgateway.geoph.core.repositories.ProjectRepository;
import org.devgateway.geoph.model.GeoPhoto;
import org.devgateway.geoph.model.GeoPhotoSource;
import org.devgateway.geoph.persistence.spring.PersistenceApplication;
import org.devgateway.geoph.services.spring.ServicesApplication;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebas on 8/30/2016.
 */

@SpringBootApplication
@Import({PersistenceApplication.class, ServicesApplication.class})
@EnableJpaRepositories("org.devgateway.geoph")
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource(value = "file:${CONF_FILE}", ignoreResourceNotFound = true)
})

@Component
public class KMLImport implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(KMLImport.class);

    public static void main(String[] args) {
        SpringApplication.run(KMLImport.class, args);
    }


    @Autowired
    GeoPhotoRepository1 geoPhotoRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Value("${path}")
    private String kmlFolder;



    private void readFeature(Feature feature){
        geoPhotoRepository.deleteAll();
     //   LOGGER.info(feature.getName());

        if (feature instanceof Folder) {
            List<Feature> folders= ((Folder) feature).getFeature();
            folders.forEach(f ->{
                readFeature(f);
            } );
        }
        if (feature instanceof Document){
            Document doc=(Document) feature;
             List<Feature> features=doc.getFeature();
            features.forEach(f-> {
                readFeature(f);
            });

        }
        if (feature instanceof  Placemark){
            Placemark placemark=(Placemark)feature;
            String name=placemark.getName();
            String description=placemark.getDescription();
            de.micromata.opengis.kml.v_2_2_0.Point geometry= (de.micromata.opengis.kml.v_2_2_0.Point) placemark.getGeometry();
            GeoPhoto photo=new GeoPhoto();
            photo.setDescription(description);
            photo.setName(name);
            GeometryFactory gf = new GeometryFactory();
            org.jsoup.nodes.Document doc = Jsoup.parse(description);
            Elements elements=doc.getElementsByTag("img");
            List<String> urls=new ArrayList<>();
            LOGGER.info(String.valueOf(elements.size()));
            elements.forEach(element -> {

                String img = element.attr("src");
                if (img.lastIndexOf("logo_kmz.gif") == -1) {
                    LOGGER.info(img);
                    urls.add(img);
                }
            });

            photo.setUrl(urls);

            com.vividsolutions.jts.geom.Coordinate coord = new com.vividsolutions.jts.geom.Coordinate(geometry.getCoordinates().get(0).getLatitude(),geometry.getCoordinates().get(0).getLongitude());
            com.vividsolutions.jts.geom.Point point = gf.createPoint(coord);

            photo.setPoint(point);
            geoPhotoRepository.save(photo);
            geoPhotoRepository.flush();

        }
    }



    public void run(String... strings) throws Exception {

        File folder=new File(kmlFolder);

        File[] files =folder.listFiles();
                for(File f:files){
                    if (f.isDirectory()){
                        String name=f.getName(); //name should be project id
                        File[] kmlsFound=f.listFiles((dir, n) -> n.endsWith(".kml"));
                        if (kmlsFound.length>0){
                            Kml kml= Kml.unmarshal(kmlsFound[0]);
                            Feature root=kml.getFeature();
                            readFeature(root);
                        }

                    }
                }


    }
}
