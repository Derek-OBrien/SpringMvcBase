package com.gm.spring.services;

import com.gm.spring.utils.Utils;
import org.springframework.stereotype.Component;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.gm.spring.utils.Constants.*;

@Component
public class RecordsService{

    private Utils util;

    public RecordsService(Utils util){
        this.util = util;
    }
    /**
     * Get files using utils class
     * @param type : type of file to get, full, action, spec
     * @return list of file names
     */
    public List<String> getFiles(String type){

        List<File> files = this.util.getFiles(type, "csv");
        List<String> filesNames = new ArrayList<>();

        for(File f : files){
            filesNames.add(f.getName());
        }

        return filesNames;
    }


    /**
     * Download a single file
     * @param response Servlet response to deliver the file
     * @param fileName The name of the file to download
     * @param type The type of the file to download
     * @throws IOException
     */

    public void downloadFile(HttpServletResponse response, String fileName, String type) throws IOException {
        List<File> fileList = util.getFiles(type, "csv");

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename" + fileName);

        for (File file : fileList) {
            if (file.getName().contains(fileName)) {
                response.setHeader("Content-Length", String.valueOf(file.length()));

                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                IOUtils.copy(inputStream, response.getOutputStream());

                response.getOutputStream().close();
                inputStream.close();
                file.delete();
            } else {
                System.out.println("File : " + fileName + " could not be found");
            }
        }
    }

    /**
     * Download all the files of same type
     * @param response Servlet response to hold deliver zip file
     * @param type type of file to download
     * @throws IOException
     */

    public void downloadAll(HttpServletResponse response, String type) throws IOException {
        List<File> fileList = util.getFiles(type, "csv");

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + type + ".zip\"");

        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());


        //packing files
        for (File file : fileList) {
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            FileInputStream fileInputStream = new FileInputStream(file);

            IOUtils.copy(fileInputStream, zipOutputStream);

            fileInputStream.close();
            zipOutputStream.closeEntry();

            file.delete();
        }

        zipOutputStream.close();
        response.getOutputStream().close();


    }


    /**
     *  Delete the selected file
     * @param fileName File to delete
     * @param type type of file to delete
     * @return string output message
     */
    public String deleteFile(String fileName, String type) {
        List<File> fileList = this.util.getFiles(type, "csv");
        String output = "";

        for (File file : fileList) {

            if (file.getName().contains(fileName)) {

                if (file.delete()) {
                    output = file.getName() + " : Has been deleted!!";
                } else {
                    output = file.getName() + " : Has not been deleted!!";
                }
            } else {
                output = "File not found!!";
            }
        }
        return output;
    }

    /**
     * Delete all files of type
     * @param type the type of files to delete
     * @return string output message
     */
    public String deleteAll(String type) {

        List<File> fileList = this.util.getFiles(type, "csv");
        String output = "";

        for (File file : fileList) {
            if (file.delete()) {
                output = file.getName() + " : Has been deleted!!";
            } else {
                output = file.getName() + " : Has not been deleted!!";
            }
        }
        return output;
    }

    /**
     * Search for files
     * @param searchString string to use in the search
     * @return list of files found by search
     */
    public List<String> searchFor(String searchString) {
        List<String> specSearch = this.getFiles(FILE_TYPE_CSV_SPEC);
        List<String> actionSearch = this.getFiles(FILE_TYPE_CSV_ACTION);
        List<String> fullSearch = this.getFiles(FILE_TYPE_CSV_FULL);

        List<String> result = new ArrayList<>();
        List<String> searchTerms = new ArrayList<>();


        Scanner scanner = new Scanner(searchString);
        scanner.useDelimiter(",");

        while(scanner.hasNext()){
            searchTerms.add(scanner.next().trim());
        }
        scanner.close();

        for(String term : searchTerms){
            if(!term.equals("")) {
                for (String s : specSearch) {
                    if (s.toUpperCase().contains(term.toUpperCase())) {
                        result.add(s);
                    }
                }
                for (String s : actionSearch) {
                    if (s.toUpperCase().contains(term.toUpperCase())) {
                        result.add(s);
                    }
                }
                for (String s : fullSearch) {
                    if (s.toUpperCase().contains(term.toUpperCase())) {
                        result.add(s);
                    }
                }
            }
        }
        return result;
    }
}
