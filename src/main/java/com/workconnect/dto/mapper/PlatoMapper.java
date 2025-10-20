package com.workconnect.dto.mapper;

import org.springframework.stereotype.Component;
import com.workconnect.dto.PlatoRequestDTO;
import com.workconnect.dto.PlatoResponseDTO;
import com.workconnect.model.Plato;

@Component
public class PlatoMapper {

    public Plato toEntity(PlatoRequestDTO dto) {
        return Plato.builder().nombre(dto.getNombre()).descripcion(dto.getDescripcion())
                .disponible(dto.getDisponible()).tipoPlato(dto.getTipoPlato()).build();
    }

    public PlatoResponseDTO toDto(Plato plato) {
        PlatoResponseDTO dto = new PlatoResponseDTO();
        dto.setId(plato.getId());
        dto.setNombre(plato.getNombre());
        dto.setDescripcion(plato.getDescripcion());
        dto.setDisponible(plato.getDisponible());
        dto.setTipoPlato(plato.getTipoPlato());
        return dto;
    }
}
