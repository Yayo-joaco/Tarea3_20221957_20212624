package com.example.Clase3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/history") //  Map to /history
public class HistoryController {

    @GetMapping("") //  Handles /history
    public String listHistory(Model model, @RequestParam(value = "texto", required = false) String texto) {
        //  Implement your logic to fetch history data here
        //  For example:
        //  List<JobHistory> historyList = historyRepository.findAll();
        //  model.addAttribute("historyList", historyList);

        return "historial"; //  Corrected: historial.html is in templates/
    }

    //  Add other methods as needed for your history functionality
}