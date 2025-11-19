package com.workconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workconnect.model.Plato;
import com.workconnect.model.TipoPlato;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Long> {
    
    Optional<Plato> findByNombre(String nombre);
    
    List<Plato> findByTipoPlato(TipoPlato tipoPlato);
    
    List<Plato> findByDisponible(Boolean disponible);
    
    List<Plato> findByTipoPlatoAndDisponible(TipoPlato tipoPlato, Boolean disponible);
    
    boolean existsByNombre(String nombre);
}