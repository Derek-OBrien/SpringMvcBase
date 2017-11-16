package com.gm.spring.controllers;

import com.gm.spring.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.gm.spring.services.IndexService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import static com.gm.spring.utils.Constants.ROOT_PATH;


@Controller
@RequestMapping("/")
public class IndexController {

    private IndexService indexService;
    private Utils util;


    @Autowired
    public IndexController(IndexService service, Utils util){
        this.indexService = service;
        this.util = util;
    }


    @RequestMapping(value="/")
    public String index(Model m){
        m.addAttribute("message","Enter folder path and press the button to start conversion");

        return"index";
    }


    @RequestMapping(value="convert")
    public String indexBtn(Model m){//, @RequestParam(value="foldertoupload") String folder) {
//
//        if(!folder.equals("")) {
//            util.setRootFolder(folder);
//            m.addAttribute("message", this.indexService.run(ROOT_PATH));
//        }
//        else{
//            m.addAttribute("message", "<span style=\"color:red; font-weight:bold;\">Please enter the path of the folder to search</span>");
//        }

        m.addAttribute("message", this.indexService.run(ROOT_PATH));
        return "index";
   }


    /**
     * Upload multiple file using Spring Controller
     */
    @RequestMapping(value = "uploadMultipleFile")
    public String uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files, Model m) {

        m.addAttribute("message", this.indexService.uploadFiles(files));
        return "index";
    }


}



