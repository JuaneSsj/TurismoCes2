// ServicioComplementarioRepository.java (solo repo)
package org.example.turismojdbc.repository;

import org.example.turismojdbc.model.ServicioComplementario;
import org.example.turismojdbc.model.enums.TipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ServicioComplementarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ServicioComplementario> findAll() {
        String sql = "SELECT * FROM serviciocomplementario";
        return jdbcTemplate.query(sql, new ServicioRowMapper());
    }

    public Optional<ServicioComplementario> findById(Long id) {
        String sql = "SELECT * FROM serviciocomplementario WHERE id = ?";
        try {
            ServicioComplementario servicio = jdbcTemplate.queryForObject(sql, new ServicioRowMapper(), id);
            return Optional.ofNullable(servicio);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static class ServicioRowMapper implements RowMapper<ServicioComplementario> {
        @Override
        public ServicioComplementario mapRow(ResultSet rs, int rowNum) throws SQLException {
            ServicioComplementario servicio = new ServicioComplementario();
            servicio.setId(rs.getLong("id"));
            servicio.setNombre(rs.getString("nombre"));
            servicio.setDescripcion(rs.getString("descripcion"));
            servicio.setCosto(rs.getDouble("costo"));
            servicio.setTipo(TipoServicio.valueOf(rs.getString("tipo").toUpperCase()));
            return servicio;
        }
    }

    public void save(ServicioComplementario servicio) {
        String sql = "INSERT INTO serviciocomplementario (nombre, descripcion, costo, tipo) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setDouble(3, servicio.getCosto());
            ps.setString(4, servicio.getTipo().name());
            return ps;
        }, keyHolder);

        // Asigna el ID generado al objeto
        servicio.setId(keyHolder.getKey().longValue());
    }


}
