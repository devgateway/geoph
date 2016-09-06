package org.devgateway.geoph.importer.spring;

import com.vividsolutions.jts.geom.GeometryFactory;
import de.micromata.opengis.kml.v_2_2_0.*;
import org.devgateway.geoph.core.repositories.GeoPhotoRepository;
import org.devgateway.geoph.core.repositories.ProjectRepository;
import org.devgateway.geoph.model.GeoPhoto;
import org.devgateway.geoph.model.GeoPhotoUrls;
import org.devgateway.geoph.model.Project;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sebas on 8/30/2016.
 */


@Service
public class KMLImport {

    private static final Logger LOGGER = LoggerFactory.getLogger(KMLImport.class);
    private static final int DESCRIPTION_MAX_LENGTH = 5000;
    private static final int NAME_MAX_LENGTH = 255;

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
                GeoPhoto photo = new GeoPhoto();
                photo.setDescription(getDescription(description));
                photo.setName(getName(name));
                GeometryFactory gf = new GeometryFactory();
                if (description != null) {
                    org.jsoup.nodes.Document doc = Jsoup.parse(description);
                    Elements elements = doc.getElementsByTag("img");
                    Set<GeoPhotoUrls> urls = new HashSet<>();
                    LOGGER.info(String.valueOf(elements.size()));
                    elements.forEach(element -> {
                        String img = element.attr("src");
                        if (img.lastIndexOf("logo_kmz.gif") == -1) {
                            LOGGER.info(img);
                            if (img.startsWith("files")) {
                                img = path_prefix + "/" + img;
                            }
                            urls.add(new GeoPhotoUrls(photo, img));
                        }
                    });
                    photo.setUrls(urls);
                }
                if (photo.getUrls()!=null && photo.getUrls().size()>0) {
                    de.micromata.opengis.kml.v_2_2_0.Point geometry = (de.micromata.opengis.kml.v_2_2_0.Point) placemark.getGeometry();
                    com.vividsolutions.jts.geom.Coordinate coordinate = new com.vividsolutions.jts.geom.Coordinate(geometry.getCoordinates().get(0).getLatitude(), geometry.getCoordinates().get(0).getLongitude());
                    com.vividsolutions.jts.geom.Point point = gf.createPoint(coordinate);

                    photo.setPoint(point);
                    photo.setProject(p);
                    geoPhotoRepository.save(photo);
                    geoPhotoRepository.flush();
                }
            } catch (Exception e){
                LOGGER.error("Error at: " + e.getMessage());
            }


        }
    }

    private String getName(String name) {
        return name!=null && name.length() > NAME_MAX_LENGTH ? name.substring(0, NAME_MAX_LENGTH) : name;
    }

    private String getDescription(String description) {
        return description!=null && description.length() > DESCRIPTION_MAX_LENGTH ? description.substring(0, DESCRIPTION_MAX_LENGTH) : description;
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
