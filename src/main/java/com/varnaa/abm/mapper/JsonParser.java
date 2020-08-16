package com.varnaa.abm.mapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Author: @varnaa
 * created on 09/08/20
 **/

@Service
public class JsonParser {

    public void parse(int code, String responseBody, Set<String> mappings, String query) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
        JSONArray itemsArray = (JSONArray) jsonObject.get("items");
        if (code == 2)
            parseCodeRequestArray(itemsArray, mappings, query);
        else
            parseIssueRequestArray(itemsArray, mappings, query);
    }

    private void parseCodeRequestArray(JSONArray itemsArray, Set<String> mappings, String query) {
        if (itemsArray == null || itemsArray.size() == 0)
            return;
        for (int i = 0; i < itemsArray.size(); i++) {
            JSONObject item = (JSONObject) itemsArray.get(i);
            String mapping = String.valueOf(item.get("name"));
            if (mapping.toLowerCase().contains("test"))
                continue;
            if (mapping.toLowerCase().contains(query.toLowerCase()))
                mappings.add(mapping);
        }
    }

    private void parseIssueRequestArray(JSONArray itemsArray, Set<String> mappings, String query) {
        for (int i = 0; i < itemsArray.size(); i++) {
            JSONObject item = (JSONObject) itemsArray.get(i);
            String mapping = String.valueOf(item.get("name"));
            mappings.add(mapping);
        }
    }

}
