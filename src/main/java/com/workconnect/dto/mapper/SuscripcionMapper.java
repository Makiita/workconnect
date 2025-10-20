package com.workconnect.dto.mapper;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.workconnect.dto.SuscripcionRequestDTO;
import com.workconnect.dto.SuscripcionResponseDTO;
import com.workconnect.model.Suscripcion;

@Component
public class SuscripcionMapper {
    
    private final ClienteMapper clienteMapper;

    public SuscripcionMapper(ClienteMapper clienteMapper) {
        this.clienteMapper = clienteMapper;
    }

    public Suscripcion toEntity(SuscripcionRequestDTO dto) {
        return Suscripcion.builder()
                .nombrePlan(dto.getNombrePlan())
                .precio(dto.getPrecio())
                .tipoRenovacion(dto.getTipoRenovacion())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .build();
    }

    public SuscripcionResponseDTO toDto(Suscripcion suscripcion) {
        SuscripcionResponseDTO dto = new SuscripcionResponseDTO();
        dto.setId(suscripcion.getId());
        dto.setNombrePlan(suscripcion.getNombrePlan());
        dto.setPrecio(suscripcion.getPrecio());
        dto.setTipoRenovacion(suscripcion.getTipoRenovacion());
        dto.setFechaInicio(suscripcion.getFechaInicio());
        dto.setFechaFin(suscripcion.getFechaFin());
        if (suscripcion.getClientes() != null) {
            dto.setClientes(suscripcion.getClientes().stream()
                    .map(clienteMapper::toDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}