package org.example.turismojdbc.repository;

import org.example.turismojdbc.model.PaqueteTuristico;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaqueteTuristicoRepository extends CrudRepository<PaqueteTuristico, Long> {

    List<PaqueteTuristico> findAllByAgenciaId(Long agenciaId); // para ver paquetes por agencia
}