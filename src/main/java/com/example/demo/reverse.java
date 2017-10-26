package com.example.demo;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by atulsahaney on 9/11/17.
 */

@Service
public class reverse {

    public boolean isNumber(String str) {
        return StringUtils.isNumeric(str);
    }

    public String reversed(String str) {

        return "";
    }
}
