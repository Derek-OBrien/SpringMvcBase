package com.gm.spring.utils;

import javax.annotation.Resource;
import java.io.File;


public class Constants {

   // public static final String ROOT_PATH = "C:\\Dev\\tempFiles";

    @Resource
    public static final String ROOT_PATH = System.getProperty("catalina.home") +File.separator + "temp" + File.separator + "SpringApp";

    public static final String FILE_TYPE_CSV_FULL = "csvfull";
    public static final String FILE_TYPE_CSV_ACTION = "csvaction";
    public static final String FILE_TYPE_CSV_SPEC = "csvspec";

}
