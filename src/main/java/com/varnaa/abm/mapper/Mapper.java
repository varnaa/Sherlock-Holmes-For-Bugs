package com.varnaa.abm.mapper;

import com.uttesh.exude.exception.InvalidDataException;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.*;

/**
 * Author: @varnaa
 * created on 01/08/20
 **/

@Service
public class Mapper {
    private final Http Http;
    private final JsonParser parser;
    private final Stemmer stemmer;


    public Mapper(Http Http, Stemmer stemmer, JsonParser parser) {
        this.Http = Http;
        this.stemmer = stemmer;
        this.parser = parser;
    }

    public List<String> findCodeMappings(String description, String actualResult, String expectedResult) throws InvalidDataException, InterruptedException, ParseException, IOException {
        Http.initializeProperties();
        List<String> APIqueries = stemmer.removeStopWords(description, expectedResult, actualResult);
        System.out.println(APIqueries);
        return sendCodeRequest(APIqueries);
    }

    public List<String> findIssueMappings(String description, String actualResult, String expectedResult) throws InvalidDataException, InterruptedException, ParseException, IOException {
        Http.initializeProperties();
        List<String> APIqueries = stemmer.removeStopWords(description, expectedResult, actualResult);
        return sendIssueRequest(APIqueries);
    }

    private List<String> sendIssueRequest(List<String> APIqueries) throws IOException, InterruptedException, ParseException {
        TreeSet<String> mappings = new TreeSet<>();
        HttpClient client = Http.getHttp();
        for (String query : APIqueries) {
            HttpResponse<String> response = client.send(Http.getIssueRequest(query), HttpResponse.BodyHandlers.ofString());
            parser.parse(1, response.body(), mappings, query); // code to differentiate between issue search and code search
        }
        return new ArrayList<>(mappings);
    }

    private List<String> sendCodeRequest(List<String> APIqueries) throws IOException, InterruptedException, ParseException {
        Set<String> mappings = new HashSet<>();
        HttpClient client = Http.getHttp();
        for (String query : APIqueries) {
            HttpResponse<String> response = client.send(Http.getCodeRequest(query), HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 403)
                continue;
            System.out.println(response.toString());
            parser.parse(2, response.body(), mappings, query); // code to differentiate between issue search and code search
        }
        return new ArrayList<>(mappings);
    }
}

