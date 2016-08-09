package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.services.FileService;
import org.devgateway.geoph.core.services.ScreenCaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by sebas on 8/3/2016.
 */

@RestController
@RequestMapping(value = "/export")
public class MapExport {

    private final ScreenCaptureService screenCaptureService;

    @Autowired
    FileService fileService;

    @Autowired
    public MapExport(ScreenCaptureService screenCaptureService) {
        this.screenCaptureService = screenCaptureService;
    }

    @RequestMapping(value = "/pdf", produces = "application/json")
    public HashMap<String,String> toPdf(@RequestBody Map<String, String> params, HttpServletResponse response) throws Exception {

        Integer width = Integer.parseInt(params.get("width"));
        Integer height = Integer.parseInt(params.get("height"));
        String html = params.get("html");
        String name = screenCaptureService.createPdfFromHtmlString(width, height, html);
        HashMap values=new HashMap();
        values.put("file",name);
        return values;
    }

    @RequestMapping(value = "/download/{name:.+}   ",method = GET)
    public void download(HttpServletResponse response,@PathVariable String name) throws Exception {

        File file = fileService.getFile(name);;


        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : "+mimeType);

        response.setContentType(mimeType);

        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));


        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

}
