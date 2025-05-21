package org.example.turismojdbc.repository;

import org.example.turismojdbc.model.Sede;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SedeRepository extends CrudRepository<Sede, Long> {
    Optional<Sede> findByAgenciaId(Long agenciaId);
}