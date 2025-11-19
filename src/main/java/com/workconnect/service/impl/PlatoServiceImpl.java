package com.workconnect.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workconnect.dto.PlatoRequestDTO;
import com.workconnect.dto.PlatoResponseDTO;
import com.workconnect.dto.mapper.PlatoMapper;
import com.workconnect.model.Plato;
import com.workconnect.model.TipoPlato;
import com.workconnect.repository.PlatoRepository;
import com.workconnect.service.PlatoService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class PlatoServiceImpl implements PlatoService {

    private final PlatoRepository platoRepository;
    private final PlatoMapper platoMapper;

    public PlatoServiceImpl(PlatoRepository platoRepository, PlatoMapper platoMapper) {
        this.platoRepository = platoRepository;
        this.platoMapper = platoMapper;
    }

    @Override
    public PlatoResponseDTO crearPlato(PlatoRequestDTO platoRequestDTO) {
        if (platoRepository.existsByNombre(platoRequestDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe un plato con ese nombre");
        }
        
        Plato plato = platoMapper.toEntity(platoRequestDTO);
        plato = platoRepository.save(plato);
        return platoMapper.toDto(plato);
    }

    @Override
    public PlatoResponseDTO actualizarPlato(Long id, PlatoRequestDTO platoRequestDTO) {
        Plato platoExistente = platoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plato no encontrado con id: " + id));
        
        // Verificar si el nuevo nombre ya existe en otro plato
        if (!platoExistente.getNombre().equals(platoRequestDTO.getNombre()) &&
            platoRepository.existsByNombre(platoRequestDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe un plato con ese nombre");
        }
        
        platoExistente.setNombre(platoRequestDTO.getNombre());
        platoExistente.setDescripcion(platoRequestDTO.getDescripcion());
        platoExistente.setDisponible(platoRequestDTO.getDisponible());
        platoExistente.setTipoPlato(platoRequestDTO.getTipoPlato());
        
        platoExistente = platoRepository.save(platoExistente);
        return platoMapper.toDto(platoExistente);
    }

    @Override
    public void eliminarPlato(Long id) {
        if (!platoRepository.existsById(id)) {
            throw new EntityNotFoundException("Plato no encontrado con id: " + id);
        }
        platoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PlatoResponseDTO obtenerPlatoPorId(Long id) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plato no encontrado con id: " + id));
        return platoMapper.toDto(plato);
    }

    @Override
    @Transactional(readOnly = true)
    public PlatoResponseDTO obtenerPlatoPorNombre(String nombre) {
        Plato plato = platoRepository.findByNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("Plato no encontrado con nombre: " + nombre));
        return platoMapper.toDto(plato);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlatoResponseDTO> obtenerTodosLosPlatos() {
        return platoRepository.findAll().stream()
                .map(platoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlatoResponseDTO> obtenerPlatosPorTipo(TipoPlato tipoPlato) {
        return platoRepository.findByTipoPlato(tipoPlato).stream()
                .map(platoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlatoResponseDTO> obtenerPlatosDisponibles() {
        return platoRepository.findByDisponible(true).stream()
                .map(platoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlatoResponseDTO> obtenerPlatosDisponiblesPorTipo(TipoPlato tipoPlato) {
        return platoRepository.findByTipoPlatoAndDisponible(tipoPlato, true).stream()
                .map(platoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void cambiarDisponibilidad(Long id, Boolean disponible) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plato no encontrado con id: " + id));
        plato.setDisponible(disponible);
        platoRepository.save(plato);
    }
}