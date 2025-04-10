package com.example.Clase3.controller;

import com.example.Clase3.entity.Employee;
import com.example.Clase3.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("")
    public String listEmployees(Model model, @RequestParam(value = "texto", required = false) String texto) {
        List<Employee> list;

        if (texto != null && !texto.isEmpty()) {
            list = employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(texto, texto);
        } else {
            list = employeeRepository.findAll();
        }

        model.addAttribute("employeeList", list);
        return "list"; //  Corrected: list.html is in templates/
    }

    @GetMapping("/new")
    public String newEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "nuevo"; // Corrected: nuevo.html is in templates/
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        employeeRepository.save(employee);
        redirectAttributes.addFlashAttribute("msg", "Empleado creado exitosamente");
        return "redirect:/employees";
    }

    @GetMapping("/edit")
    public String editEmployeeForm(@RequestParam("id") int id, Model model) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "nuevo"; //   Corrected:  Using nuevo.html for edit too
        } else {
            return "redirect:/employees";
        }
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        employeeRepository.save(employee);
        redirectAttributes.addFlashAttribute("msg", "Empleado actualizado exitosamente");
        return "redirect:/employees";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        employeeRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("msg", "Empleado borrado exitosamente");
        return "redirect:/employees";
    }
}