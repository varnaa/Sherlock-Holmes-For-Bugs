package com.varnaa.abm.mapper;

import com.varnaa.abm.AbmApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: @varnaa
 * created on 01/08/20
 **/

@Component
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(AbmApplication.class);

    @Autowired
    private ProjectProperities projectProperities;
    @Autowired
    private Http Http;

    public void mapper() {
        Http.initializeProperies();
        System.out.println(Http.getCodeRequest("jsdhkj").toString());
        System.out.println(Http.getIssueRequest("dagfa").toString());
    }
}
