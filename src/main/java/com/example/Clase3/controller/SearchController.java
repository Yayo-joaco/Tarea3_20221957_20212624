package com.example.Clase3.controller;

import com.example.Clase3.repository.DepartmentRepository;
import com.example.Clase3.repository.JobHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate; // <-- Importar LocalDate

@Controller
@RequestMapping("/search") // Map to /search
public class SearchController {

    @Autowired
    JobHistoryRepository jobHistoryRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("") // Handles /search
    public String showSearchPage(Model model, @RequestParam(value = "texto", required = false) String texto) {
        // Implement your search logic here or redirect to a report index
        // For now, just return the index view
        return "index"; // or "search/index" if you create a specific index for reports
    }

    @GetMapping("/reporte/salario")
    public String reporteSalario(Model model) {
        model.addAttribute("listaHistorial", jobHistoryRepository.findJobHistoryBySalaryGreaterThan15000());
        return "reportes/salario"; // Path to the Thymeleaf template
    }

    @GetMapping("/reporte/departamentos")
    public String reporteDepartamentos(Model model) {
        model.addAttribute("listaDepartamentos", departmentRepository.findDepartmentsByCountryAndCityWithMoreThan3Employees());
        return "reportes/departamentos"; // Path to the Thymeleaf template
    }

    @GetMapping("/reporte/gerentes")
    public String reporteGerentes(Model model) {
        LocalDate cutoffDate = LocalDate.now().minusYears(5); // <-- Calcular fecha de corte
        model.addAttribute("listaGerentes", departmentRepository.findManagersWithMoreThan5YearsExperience(cutoffDate)); // <-- Pasar fecha como argumento
        return "reportes/gerentes"; // Path to the Thymeleaf template
    }

    // Add other methods for your search functionality if needed
}