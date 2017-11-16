package com.gm.spring.services;

import com.gm.spring.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SortRecordService {

    /**
     * Sort file list by name Ascending order
     * @param list : list to sort
     * @return sorted list
     */
    public List<String> sortbyName(List<String> list){
        Collections.sort(list);
        return list;
    }

    /**
     * Sort file list by name Descending
     * @param list : list to sort
     * @return sorted list
     */
    public List<String> sortbyNameReverse(List<String> list){
        Collections.reverse(list);
        return list;
    }

    public List<String> sortbyFileSize(List<String> list){

        return list;
    }
}
