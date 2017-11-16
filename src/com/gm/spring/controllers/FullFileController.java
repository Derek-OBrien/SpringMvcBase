package com.gm.spring.controllers;

import com.gm.spring.services.RecordsService;
import com.gm.spring.services.SortRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.gm.spring.utils.Constants.FILE_TYPE_CSV_FULL;

@Controller
public class FullFileController {


    private RecordsService recordsService;
    private SortRecordService sortRecordService;
    private static List<String> fileList;


    @Autowired
    public FullFileController(RecordsService recordsService, SortRecordService sortRecordService){

        this.recordsService = recordsService;
        this.sortRecordService = sortRecordService;
    }

    @RequestMapping("/full")
    public String index(Model m){

        FullFileController.fileList = this.recordsService.getFiles(FILE_TYPE_CSV_FULL);

        if(FullFileController.fileList.isEmpty()){
            m.addAttribute("message", "Sorry no files found!!");
        }
        else {
            m.addAttribute("message", "Files contain both Action and Spec records!!");
            m.addAttribute("lists", FullFileController.fileList);
        }
        return "full";
    }

    @RequestMapping(value = "/downloadFull/{fileName}")
    public void downloadFile(HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
        recordsService.downloadFile(response, fileName,FILE_TYPE_CSV_FULL);
    }

    @RequestMapping(value = "/downloadFullAll", produces="application/zip")
    public void downloadAll(HttpServletResponse response) throws IOException{
        recordsService.downloadAll(response,FILE_TYPE_CSV_FULL);
    }

    @RequestMapping(value = "/deleteFull-{fileName}")
    public String deleteFile(@PathVariable("fileName") String fileName, HttpServletRequest request){
        recordsService.deleteFile(fileName, FILE_TYPE_CSV_FULL);
        return "redirect:"+ request.getHeader("Referer");
    }

    @RequestMapping(value = "/deleteFullAll")
    public String deleteAll(HttpServletRequest request){
        recordsService.deleteAll(FILE_TYPE_CSV_FULL);
        return "redirect:"+ request.getHeader("Referer");
    }


    @RequestMapping(value="/sortfull")
    public String OrderResults(@RequestParam(value="sortBtn") String orderString, Model m){
        System.out.println("Order String : " + orderString);
        if(!FullFileController.fileList.isEmpty()) {
            if (orderString.equals("asc")) {
                m.addAttribute("message", "Files sorted in Ascending order!!");
                m.addAttribute("lists", sortRecordService.sortbyName(FullFileController.fileList));
            } else if (orderString.equals("desc")) {
                m.addAttribute("message", "Files sorted in Descending order!!");
                m.addAttribute("lists", sortRecordService.sortbyNameReverse(FullFileController.fileList));
            }
        }
        else{
            m.addAttribute("message", "No file found, return to <a href=\"/\">home</a> page and run conversion");
        }
        return "full";
    }
}
