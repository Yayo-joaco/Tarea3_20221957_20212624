package com.example.Clase3.repository;

import com.example.Clase3.dto.ReporteCiudadDTO;
import com.example.Clase3.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Búsqueda por nombre o apellido (ya lo tienes probablemente)
    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String nombre, String apellido);

    // Consulta para el reporte por ciudad
    @Query("SELECT new com.example.Clase3.dto.ReporteCiudadDTO(l.city, COUNT(e)) " +
            "FROM Employee e " +
            "JOIN e.department d " +
            "JOIN d.location l " +
            "GROUP BY l.city")
    List<ReporteCiudadDTO> contarEmpleadosPorCiudad();
}