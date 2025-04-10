package com.example.Clase3.repository;

import com.example.Clase3.entity.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Integer> { // Or appropriate ID type

    @Query("SELECT jh FROM JobHistory jh " +
            "WHERE LOWER(jh.employee.firstName) LIKE LOWER(concat('%', :texto, '%')) OR " +
            "LOWER(jh.employee.lastName) LIKE LOWER(concat('%', :texto, '%')) OR " +
            "LOWER(jh.job.jobTitle) LIKE LOWER(concat('%', :texto, '%')) OR " +
            "LOWER(jh.department.departmentName) LIKE LOWER(concat('%', :texto, '%'))")
    List<JobHistory> searchHistory(String texto);
}