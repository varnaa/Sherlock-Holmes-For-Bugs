package com.varnaa.abm.mapper;

import com.uttesh.exude.ExudeData;
import com.uttesh.exude.exception.InvalidDataException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author: @varnaa
 * created on 09/08/20
 **/

@Service
public class Stemmer {
    public Stemmer() {
    }

    public List<String> removeStopWords(String description, String expectedResult, String actualResult) throws InvalidDataException {
        StringBuilder data = new StringBuilder();
        data.append(description)
                .append(" ")
                .append(expectedResult)
                .append(" ")
                .append(actualResult);

        String processedData = ExudeData.getInstance().filterStoppingsKeepDuplicates(data.toString().toLowerCase());
        return getKeywords(processedData);
    }

    private List<String> getKeywords(String data) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : data.split(" ")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> keywords = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) > 3)
                keywords.add(key);
        }
        return keywords;
    }


}
