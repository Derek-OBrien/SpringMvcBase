package com.gm.spring.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.gm.spring.utils.Constants.ROOT_PATH;

@Component
public class Utils {

    private Resource[] resources;
    private ApplicationContext appContext = new FileSystemXmlApplicationContext();

    private String filePath;

    /**
     * Get List of file as files
     * @param ext : file extention to get "csv"
     * @param folder : folder to search for "csvaction" "csvspec" "csvfull"
     * @return List<Files> containing all the file names in the specified folder
     */
    public List<File> getFiles(String folder, String ext){
        List<File> list = new ArrayList<>();

        this.setFilePath(folder, ext);
        try {
            resources = appContext.getResources(this.getFilePath());
        }catch (IOException e){
            System.out.println("Error" + e);
        }

        for (Resource r: resources) {
            try{
                list.add(r.getFile());
            }catch (IOException e){
                System.out.println("Error : " + e);
            }
        }
        return list;
    }


    /**
     * Set filepath to search
     * @param folder : folder to search
     * @param ext : file extension to search for
     */
    private void setFilePath(String folder, String ext){
        //filePath = "file:C://resources/" + folder + "/*." + ext;

        if(ext.equals("dat")){
            filePath = "file:"+ ROOT_PATH + File.separator + ext + "/*." + ext;
        }
        else if (ext.equals("csv")){
            filePath = "file:"+ ROOT_PATH + File.separator + folder +"/*." + ext;
        }
    }

    /**
     * Get the file path after setting it
     * @return string path to file
     */
    private String getFilePath(){
        return filePath;
    }


}
