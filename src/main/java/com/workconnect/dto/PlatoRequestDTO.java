package com.workconnect.dto;

import com.workconnect.model.TipoPlato;
import lombok.Data;

@Data
public class PlatoRequestDTO {
    private String nombre;
    private String descripcion;
    private Boolean disponible;
    private TipoPlato tipoPlato;
}
