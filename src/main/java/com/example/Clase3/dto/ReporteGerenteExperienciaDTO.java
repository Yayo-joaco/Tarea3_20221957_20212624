package com.example.Clase3.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReporteGerenteExperienciaDTO {
    private String nombreDepartamento;
    private String nombreGerente;
    private String apellidoGerente;
    private Double salarioGerente;

    public ReporteGerenteExperienciaDTO(String nombreDepartamento, String nombreGerente, String apellidoGerente, Double salarioGerente) {
        this.nombreDepartamento = nombreDepartamento;
        this.nombreGerente = nombreGerente;
        this.apellidoGerente = apellidoGerente;
        this.salarioGerente = salarioGerente;
    }
}