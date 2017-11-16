package com.gm.spring.services;

import com.gm.spring.utils.Utils;

import com.gm.records.reader.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static com.gm.spring.utils.Constants.ROOT_PATH;


@Component
public class IndexService {


    public String welcomeMessage(){
        return "Welcome to Spring Mvc Base setup";
    }

    private Reader r = new Reader();

    private Utils util;

    public IndexService(Utils util){
        this.util = util;
    }


    public String run(String folder) {

        r.run(this.util.getFiles(folder, "dat"));

        return "Files have now been converted.";
    }


    public String uploadFiles(MultipartFile[] files) {

        String message = "";

        if(files.length == 0) {
            message = "No Files Selected";
        }

        for (MultipartFile f : files) {
            if(!f.getOriginalFilename().isEmpty()){
                String name = f.getOriginalFilename();

                if(!name.contains(".dat")){
                    message = "Only files of type \".dat\" can be converted";
                }
                else {
                    try {
                        // Creating the directory to store file
                        // String rootPath = System.getProperty("catalina.home");
                        // File dir = new File(rootPath + File.separator + "tmpFiles");

                        byte[] bytes = f.getBytes();
                        File dir = new File(ROOT_PATH + File.separator + "dat");

                        if (!dir.exists())
                            dir.mkdirs();

                        // Create the file on server
                        File serverFile = new File(dir.getPath() + File.separator + name);
                        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                        stream.write(bytes);
                        stream.close();

                        message = message + "You successfully uploaded file : " + name + " to " + ROOT_PATH;
                    } catch (Exception e) {
                        message = "You failed to upload " + name + " => " + e.getMessage();
                    }
                }
            }
        }
        return message;
    }

}
