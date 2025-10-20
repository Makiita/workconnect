package com.workconnect.dto.mapper;

import org.springframework.stereotype.Component;
import com.workconnect.dto.ClienteRequestDTO;
import com.workconnect.dto.ClienteResponseDTO;
import com.workconnect.model.Cliente;

@Component
public class ClienteMapper {
    
    private final SuscripcionMapper suscripcionMapper;

    public ClienteMapper(SuscripcionMapper suscripcionMapper) {
        this.suscripcionMapper = suscripcionMapper;
    }

    public Cliente toEntity(ClienteRequestDTO dto) {
        return Cliente.builder()
                .usuario(dto.getUsuario())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .direccion(dto.getDireccion())
                .ciudad(dto.getCiudad())
                .codigoPostal(dto.getCodigoPostal())
                .build();
    }

    public ClienteResponseDTO toDto(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setUsuario(cliente.getUsuario());
        dto.setEmail(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setDireccion(cliente.getDireccion());
        dto.setCiudad(cliente.getCiudad());
        dto.setCodigoPostal(cliente.getCodigoPostal());
        if (cliente.getSuscripcion() != null) {
            dto.setSuscripcion(suscripcionMapper.toDto(cliente.getSuscripcion()));
        }
        return dto;
    }
}