package com.varnaa.abm.controller;

import com.varnaa.abm.model.Bug;
import com.varnaa.abm.repository.BugRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ABMcontroller {

    private final BugRepository bugRepository;
    private final SimpleDateFormat format;

    public ABMcontroller(BugRepository repository) {
        this.bugRepository = repository;
        this.format = new SimpleDateFormat("dd-MM-yyyy");
    }

    @GetMapping("/new")
    public String createNewBug(Model model) {
        model.addAttribute("bug", new Bug());
        return "form";
    }

    @GetMapping("/save")
    public String saveBug(@ModelAttribute("bug") Bug bug) {
        bug.setDate(format.format(new Date()));
        bugRepository.save(bug);
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

}
