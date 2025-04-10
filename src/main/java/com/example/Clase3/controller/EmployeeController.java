package com.example.Clase3.controller;

import com.example.Clase3.entity.Department;
import com.example.Clase3.entity.Employee;
import com.example.Clase3.entity.Job;
import com.example.Clase3.repository.DepartmentRepository;
import com.example.Clase3.repository.EmployeeRepository;
import com.example.Clase3.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("")
    public String listEmployees(Model model, @RequestParam(value = "texto", required = false) String texto) {
        List<Employee> list;

        if (texto != null && !texto.isEmpty()) {
            list = employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(texto, texto);
        } else {
            list = employeeRepository.findAll();
        }

        model.addAttribute("employeeList", list);
        return "list";
    }

    @GetMapping("/new")
    public String newEmployeeForm(Model model) {
        log.info("Entering newEmployeeForm");
        Employee employee = new Employee();
        employee.setJob(new Job()); // Initialize Job
        employee.setDepartment(new Department()); // Initialize Department
        employee.setManager(new Employee()); // Initialize Manager
        model.addAttribute("employee", employee);
        List<Job> jobList = jobRepository.findAll();
        List<Department> departmentList = departmentRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll(); // For manager selection
        model.addAttribute("jobList", jobList);
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("employeeList", employeeList); // Add employeeList to the model
        log.info("jobList size: {}", jobList.size());
        log.info("departmentList size: {}", departmentList.size());
        log.info("employeeList size: {}", employeeList.size());
        return "nuevo";
    }


    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        employee.setHireDate(LocalDate.now());
        employeeRepository.save(employee);
        redirectAttributes.addFlashAttribute("msg", "Empleado creado exitosamente");
        return "redirect:/employees";
    }

    @GetMapping("/edit")
    public String editEmployeeForm(@RequestParam("id") int id, Model model) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            model.addAttribute("jobList", jobRepository.findAll());
            model.addAttribute("departmentList", departmentRepository.findAll());
            model.addAttribute("employeeList", employeeRepository.findAll()); // For manager selection
            return "nuevo";
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