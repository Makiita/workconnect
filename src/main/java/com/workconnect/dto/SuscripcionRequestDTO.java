package com.workconnect.dto;

import java.sql.Date;
import com.workconnect.model.RenovacionSuscripcion;
import lombok.Data;

@Data
public class SuscripcionRequestDTO {
    private String nombrePlan;
    private Double precio;
    private RenovacionSuscripcion tipoRenovacion;
    private Date fechaInicio;
    private Date fechaFin;
}