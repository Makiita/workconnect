package com.workconnect.dto;

import lombok.Data;

@Data
public class ClienteResponseDTO {
    private Long id;
    private String usuario;
    private String email;
    private String telefono;
    private String nombre;
    private String apellido;
    private String direccion;
    private String ciudad;
    private String codigoPostal;
    private SuscripcionResponseDTO suscripcion;
}