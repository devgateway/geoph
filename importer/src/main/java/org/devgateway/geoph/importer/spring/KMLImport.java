package org.devgateway.geoph.importer.spring;

import com.vividsolutions.jts.geom.GeometryFactory;
import de.micromata.opengis.kml.v_2_2_0.*;
import org.devgateway.geoph.core.repositories.GeoPhotoRepository;
import org.devgateway.geoph.core.repositories.ProjectRepository;
import org.devgateway.geoph.model.GeoPhoto;
import org.devgateway.geoph.model.Project;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebas on 8/30/2016.
 */


@Service
public class KMLImport {

    private static final Logger LOGGER = LoggerFactory.getLogger(KMLImport.class);

    @Autowired
    GeoPhotoRepository geoPhotoRepository;

    @Value("${path}")
    private String kmlFolder;


    @Autowired
    ProjectRepository projectRepository;

    private void readFeature(Project p,Feature feature,String path_prefix){

     //   LOGGER.info(feature.getName());

        if (feature instanceof Folder) {
            List<Feature> folders= ((Folder) feature).getFeature();
            folders.forEach(f ->{
                readFeature(p,f,path_prefix);
            } );
        }
        if (feature instanceof Document){
            Document doc=(Document) feature;
             List<Feature> features=doc.getFeature();
            features.forEach(f-> {
                readFeature(p,f,path_prefix);
            });

        }
        if (feature instanceof  Placemark){
            try {
                Placemark placemark = (Placemark) feature;
                String name = placemark.getName();
                String description = placemark.getDescription();
                de.micromata.opengis.kml.v_2_2_0.Point geometry = (de.micromata.opengis.kml.v_2_2_0.Point) placemark.getGeometry();
                GeoPhoto photo = new GeoPhoto();
                photo.setDescription(description);
                photo.setName(name);
                GeometryFactory gf = new GeometryFactory();
                if (description != null) {
                    org.jsoup.nodes.Document doc = Jsoup.parse(description);
                    Elements elements = doc.getElementsByTag("img");
                    List<String> urls = new ArrayList<>();
                    LOGGER.info(String.valueOf(elements.size()));
                    elements.forEach(element -> {

                        String img = element.attr("src");
                        if (img.lastIndexOf("logo_kmz.gif") == -1) {
                            LOGGER.info(img);
                            if (img.startsWith("files")) {
                                img = path_prefix + "/" + img;
                            }
                            urls.add(img);
                        }
                    });

                    photo.setUrls(urls);
                }

                com.vividsolutions.jts.geom.Coordinate coord = new com.vividsolutions.jts.geom.Coordinate(geometry.getCoordinates().get(0).getLatitude(), geometry.getCoordinates().get(0).getLongitude());
                com.vividsolutions.jts.geom.Point point = gf.createPoint(coord);

                photo.setPoint(point);
                photo.setProject(p);
                geoPhotoRepository.save(photo);
                geoPhotoRepository.flush();
            } catch (Exception e){
                LOGGER.error("Error at: " + e.getMessage());
            }


        }
    }


    private Project findProjectByPhId(String id){
        String[] parts=id.split("-");
        Project  p=projectRepository.findByPhId(id);

        if (p==null && parts.length>1){
            p=projectRepository.findByPhId(parts[0]+"-"+parts[1]);
            if (p==null){
                p=projectRepository.findByPhId(parts[1]+"-"+parts[0]);

            }
        }
        return p;
    }

    public void run(String... strings) throws Exception {
        geoPhotoRepository.deleteAll();
        File folder=new File(kmlFolder);

        File[] files =folder.listFiles();
                for(File f:files){
                    if (f.isDirectory()){
                        String phid=f.getName(); //name should be project id < get project by id
                        Project p=findProjectByPhId(phid);
                        if (p==null){
                            LOGGER.warn("Wasn't able to find a related project for this kml, records will be saved without project_id");
                        }
                        File[] kmlsFound=f.listFiles((dir, n) -> n.endsWith(".kml"));
                        if (kmlsFound.length>0){
                            Kml kml= Kml.unmarshal(kmlsFound[0]);
                            Feature root=kml.getFeature();
                            readFeature(p,root,"/kml/"+phid);
                        }

                    }
                }


    }
}
