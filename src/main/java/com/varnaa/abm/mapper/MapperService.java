package com.varnaa.abm.mapper;

import com.uttesh.exude.exception.InvalidDataException;
import com.varnaa.abm.model.Bug;
import com.varnaa.abm.repository.BugRepository;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: @varnaa
 * created on 16/08/20
 **/

@Service
public class MapperService {
    private final Mapper mapper;
    private final BugRepository repository;
    private List<String> mappings;

    public MapperService(Mapper mapper, BugRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
        this.mappings = new LinkedList<>();
    }

    public void findMappings(Bug bug) throws InterruptedException, InvalidDataException, ParseException, IOException {
        mappings = mapper.findCodeMappings(bug.getDescription(), bug.getActualResult(), bug.getExpectedResult());
        bug.setMappings(mappings);
        saveToRepository(bug);
    }

    private void saveToRepository(Bug bug) {
        repository.save(bug);
    }

}
