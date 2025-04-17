package com.example.Clase3.controller;

import com.example.Clase3.entity.JobHistory;
import com.example.Clase3.repository.JobHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @GetMapping("")
    public String listHistory(Model model, @RequestParam(value = "texto", required = false) String texto) {
        List<JobHistory> historyList;
        
        if (texto != null && !texto.isEmpty()) {
            historyList = jobHistoryRepository.searchHistory(texto);
        } else {
            historyList = jobHistoryRepository.findAll();
        }
        
        model.addAttribute("historyList", historyList);
        model.addAttribute("texto", texto);
        
        return "historial";
    }
}