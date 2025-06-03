package org.example.turismojdbc.repository;

import org.example.turismojdbc.ServicioAsignadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<ServicioAsignadoDTO> findServiciosBySedeId(Long sedeId) {
        String sql = "SELECT s.id, s.nombre, s.descripcion, ss.costo_personalizado " +
                "FROM serviciocomplementario s " +
                "JOIN sede_servicio ss ON s.id = ss.servicio_id " +
                "WHERE ss.sede_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new ServicioAsignadoDTO(
                rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("costo_personalizado")
        ), sedeId);
    }
}