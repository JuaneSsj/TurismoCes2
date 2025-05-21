package org.example.turismojdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SedeServicioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void asignarServicioASede(Long sedeId, Long servicioId) {
        String sql = "INSERT INTO sede_servicio (sede_id, servicio_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, sedeId, servicioId);
    }

    public void eliminarServicioDeSede(Long sedeId, Long servicioId) {
        String sql = "DELETE FROM sede_servicio WHERE sede_id = ? AND servicio_id = ?";
        jdbcTemplate.update(sql, sedeId, servicioId);
    }
}