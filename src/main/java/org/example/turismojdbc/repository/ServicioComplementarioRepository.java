package org.example.turismojdbc.repository;

import org.example.turismojdbc.model.ServicioComplementario;
import org.example.turismojdbc.model.enums.TipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ServicioComplementarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<ServicioComplementario> servicioRowMapper = (rs, rowNum) -> {
        ServicioComplementario servicio = new ServicioComplementario();
        servicio.setId(rs.getLong("id"));
        servicio.setNombre(rs.getString("nombre"));
        servicio.setDescripcion(rs.getString("descripcion"));
        servicio.setCosto(rs.getDouble("costo"));
        servicio.setTipo(TipoServicio.valueOf(rs.getString("tipo")));

        return servicio;
    };

    public List<ServicioComplementario> findAll() {
        String sql = "SELECT * FROM serviciocomplementario";
        return jdbcTemplate.query(sql, servicioRowMapper);
    }

    public Optional<ServicioComplementario> findById(Long id) {
        String sql = "SELECT * FROM serviciocomplementario WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, servicioRowMapper).stream().findFirst();
    }

    public void save(ServicioComplementario servicio) {
        String sql = "INSERT INTO serviciocomplementario (nombre, descripcion, costo, tipo) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, servicio.getNombre(), servicio.getDescripcion(), servicio.getCosto(), servicio.getTipo());
    }

    public void update(ServicioComplementario servicio) {
        String sql = "UPDATE serviciocomplementario SET nombre = ?, descripcion = ?, costo = ?, tipo = ? WHERE id = ?";
        jdbcTemplate.update(sql, servicio.getNombre(), servicio.getDescripcion(), servicio.getCosto(), servicio.getTipo(), servicio.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM serviciocomplementario WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}