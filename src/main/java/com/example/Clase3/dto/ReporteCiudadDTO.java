package com.example.Clase3.dto;

public class ReporteCiudadDTO {
    private String city;
    private Long employeeCount;

    public ReporteCiudadDTO(String city, Long employeeCount) {
        this.city = city;
        this.employeeCount = employeeCount;
    }

    // Getters and Setters (or use Lombok's @Getter and @Setter)

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Long employeeCount) {
        this.employeeCount = employeeCount;
    }
}