package com.varnaa.abm.controller;

import com.uttesh.exude.exception.InvalidDataException;
import com.varnaa.abm.mapper.MapperService;
import com.varnaa.abm.model.Bug;
import com.varnaa.abm.repository.BugRepository;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class ABMcontroller {

    private final BugRepository bugRepository;
    private final SimpleDateFormat format;
    private final MapperService mapperService;

    public ABMcontroller(BugRepository repository, MapperService mapperService) {
        this.bugRepository = repository;
        this.mapperService = mapperService;
        this.format = new SimpleDateFormat("dd-MM-yyyy");
    }

    @GetMapping("/new")
    public String createNewBug(Model model) {
        model.addAttribute("bug", new Bug());
        return "form";
    }

    @GetMapping("/save")
    public String saveBug(@ModelAttribute("bug") Bug bug) throws InterruptedException, InvalidDataException, ParseException, IOException {
        bug.setDate(format.format(new Date()));
        mapperService.findMappings(bug);
        return "redirect:/view";
    }

    @GetMapping("/view")
    public String viewAllBug(Model model) {
        model.addAttribute("bugs", bugRepository.findAll());
        return "table";
    }

    @GetMapping("/mappings")
    public String showReports(Model model) {
        model.addAttribute("reports", bugRepository.findAll());
        return "report";
    }

    @GetMapping("/delete/{id}")
    public String deleteBug(@PathVariable("id") String id) {
        bugRepository.deleteById(id);
        return "redirect:/view";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        System.out.println(id);
        Optional<Bug> bug = bugRepository.findById(id);
        model.addAttribute("bug", bug);
        return "updateForm";
    }


}
