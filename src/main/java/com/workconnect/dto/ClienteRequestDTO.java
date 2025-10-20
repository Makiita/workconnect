package com.workconnect.dto;

import lombok.Data;

@Data
public class ClienteRequestDTO {
    private String usuario;
    private String email;
    private String telefono;
    private String nombre;
    private String apellido;
    private String direccion;
    private String ciudad;
    private String codigoPostal;
    private Long suscripcionId;
}