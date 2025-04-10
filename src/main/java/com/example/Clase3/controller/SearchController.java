package com.example.Clase3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search") //  Map to /search
public class SearchController {

    @GetMapping("") //  Handles /search
    public String showSearchPage(Model model, @RequestParam(value = "texto", required = false) String texto) {
        //  Implement your search logic here
        //  For example:
        //  List<SearchResult> results = searchService.performSearch(texto);
        //  model.addAttribute("searchResults", results);

        return "index"; //  or "search_results", whatever your search results page is
    }

    //  Add other methods for your search functionality
}