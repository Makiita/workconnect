package com.workconnect.service;

import java.util.List;

import com.workconnect.dto.PlatoRequestDTO;
import com.workconnect.dto.PlatoResponseDTO;
import com.workconnect.model.TipoPlato;

public interface PlatoService {
    
    PlatoResponseDTO crearPlato(PlatoRequestDTO platoRequestDTO);
    
    PlatoResponseDTO actualizarPlato(Long id, PlatoRequestDTO platoRequestDTO);
    
    void eliminarPlato(Long id);
    
    PlatoResponseDTO obtenerPlatoPorId(Long id);
    
    PlatoResponseDTO obtenerPlatoPorNombre(String nombre);
    
    List<PlatoResponseDTO> obtenerTodosLosPlatos();
    
    List<PlatoResponseDTO> obtenerPlatosPorTipo(TipoPlato tipoPlato);
    
    List<PlatoResponseDTO> obtenerPlatosDisponibles();
    
    List<PlatoResponseDTO> obtenerPlatosDisponiblesPorTipo(TipoPlato tipoPlato);
    
    void cambiarDisponibilidad(Long id, Boolean disponible);
}