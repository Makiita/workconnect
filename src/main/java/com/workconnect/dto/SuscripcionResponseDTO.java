package com.workconnect.dto;

import java.sql.Date;
import java.util.List;
import com.workconnect.model.RenovacionSuscripcion;
import lombok.Data;

@Data
public class SuscripcionResponseDTO {
    private Long id;
    private String nombrePlan;
    private Double precio;
    private RenovacionSuscripcion tipoRenovacion;
    private Date fechaInicio;
    private Date fechaFin;
    private List<ClienteResponseDTO> clientes;
}