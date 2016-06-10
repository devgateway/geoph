package org.devgateway.geoph.core.services;

import java.io.File;

/**
 * Created by Sebastian Dimunzio on 6/10/2016.
 */
public interface FileService {


    File createFile(String name, Boolean override) throws Exception;

    File getFile(String name) throws Exception;
}
