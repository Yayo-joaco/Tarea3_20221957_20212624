package com.example.Clase3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteDepartamentoPaisCiudadDTO {
    private String nombrePais;
    private String nombreCiudad;
    private Long cantidadDepartamentos;

    public ReporteDepartamentoPaisCiudadDTO(String nombrePais, String nombreCiudad, Long cantidadDepartamentos) {
        this.nombrePais = nombrePais;
        this.nombreCiudad = nombreCiudad;
        this.cantidadDepartamentos = cantidadDepartamentos;
    }
}