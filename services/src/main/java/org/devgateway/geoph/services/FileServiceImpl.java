package org.devgateway.geoph.services;

import org.devgateway.geoph.core.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by Sebastian Dimunzio on 6/10/2016.
 */
@Service
public class FileServiceImpl implements FileService {
    @Value("#{environment['repository.path']}")
    String respository;

    public File createFile(String name, Boolean override) throws Exception {
        File newFile = new File(respository, name);
        if (newFile.exists() && !override) {
            throw new Exception("File already exits");
        }
        return newFile;
    }

    public File getFile(String name) throws Exception {
        File file = new File(respository, name);
        if (!file.exists()) {
            throw new Exception("File not found");
        }
        return file;
    }
}
