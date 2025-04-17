package com.example.Clase3.repository;

import com.example.Clase3.dto.ReporteDepartamentoPaisCiudadDTO;
import com.example.Clase3.dto.ReporteGerenteExperienciaDTO;
import com.example.Clase3.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    // Consulta para el reporte de departamentos por país y ciudad (más de 3 empleados)
    @Query("SELECT new com.example.Clase3.dto.ReporteDepartamentoPaisCiudadDTO(l.country.countryName, l.city, COUNT(d.id)) " +
           "FROM Department d JOIN d.location l JOIN d.employees e " +
           "GROUP BY l.country.countryName, l.city " +
           "HAVING COUNT(e.id) > 3")
    List<ReporteDepartamentoPaisCiudadDTO> findDepartmentsByCountryAndCityWithMoreThan3Employees();

    // Consulta para el reporte de gerentes con más de 5 años de experiencia
    @Query("SELECT new com.example.Clase3.dto.ReporteGerenteExperienciaDTO(d.departmentName, m.firstName, m.lastName, m.salary) " +
           "FROM Department d JOIN d.manager m " +
           "WHERE m.hireDate < :cutoffDate")
    List<ReporteGerenteExperienciaDTO> findManagersWithMoreThan5YearsExperience(@Param("cutoffDate") LocalDate cutoffDate);
}
