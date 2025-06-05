package org.example.turismojdbc.controller;

import org.example.turismojdbc.ServicioAsignadoDTO;
import org.example.turismojdbc.ServicioCostoForm;
import org.example.turismojdbc.ServicioSeleccionado;
import org.example.turismojdbc.model.Sede;
import org.example.turismojdbc.model.ServicioComplementario;
import org.example.turismojdbc.repository.SedeRepository;
import org.example.turismojdbc.repository.ServicioComplementarioRepository;
import org.example.turismojdbc.repository.SedeServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sede")
public class SedeController {

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private ServicioComplementarioRepository servicioComplementarioRepository;

    @Autowired
    private SedeServicioRepository sedeServicioRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ServicioComplementarioRepository servicioRepo;

    @GetMapping("/agregar-servicio")
    public String mostrarFormularioAgregarServicio(Model model) {
        model.addAttribute("servicioCostoForm", new ServicioComplementario());
        return "agregar-servicios";
    }

    @GetMapping("/{id}/asignar-servicios")
    public String mostrarServiciosAsignables(@PathVariable Long id, Model model) {
        Optional<Sede> sede = sedeRepository.findById(id);
        if (sede.isEmpty()) {
            return "redirect:/error";
        }
        List<ServicioComplementario> servicios = servicioComplementarioRepository.findAll();
        ServicioCostoForm servicioCostoForm = new ServicioCostoForm();
        servicioCostoForm.inicializarDesdeServicios(servicios);

        // NUEVO: obtener servicios ya asignados
        List<ServicioAsignadoDTO> serviciosAsignados = sedeServicioRepository.findServiciosBySedeId(id);

        model.addAttribute("sede", sede.get());
        model.addAttribute("servicios", servicios);
        model.addAttribute("servicioCostoForm", servicioCostoForm);
        model.addAttribute("serviciosAsignados", serviciosAsignados);

        return "agregar-servicios";
    }

    @PostMapping("/{id}/asignar-servicios")
    public String asignarServiciosExistentes(@PathVariable Long id,
                                             @ModelAttribute ServicioCostoForm form) {

        for (ServicioSeleccionado ss : form.getServiciosSeleccionados()) {
            if (Boolean.TRUE.equals(ss.getSeleccionado())) {
                // Verificar si ya existe la relación
                Integer count = jdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM sede_servicio WHERE sede_id = ? AND servicio_id = ?",
                        Integer.class, id, ss.getServicioId()
                );
                if (count != null && count == 0) {
                    // Solo insertar si NO existe
                    Double costoBase = servicioRepo.findById(ss.getServicioId())
                            .map(ServicioComplementario::getCosto)
                            .orElse(0.0);

                    jdbcTemplate.update(
                            "INSERT INTO sede_servicio (sede_id, servicio_id, costo_personalizado) VALUES (?, ?, ?)",
                            id, ss.getServicioId(), costoBase
                    );
                }
            }
        }

        return "redirect:/sede/" + id + "/asignar-servicios";
    }

    @PostMapping("/{id}/agregar-servicio-manual")
    public String agregarServicioManual(@PathVariable Long id,
                                        @ModelAttribute ServicioCostoForm form) {

        ServicioComplementario nuevo = form.getNuevoServicio();
        servicioRepo.save(nuevo);

        // Insertar en sede_servicio con el costo base del nuevo servicio
        jdbcTemplate.update("INSERT INTO sede_servicio (sede_id, servicio_id, costo_personalizado) VALUES (?, ?, ?)",
                id, nuevo.getId(), nuevo.getCosto());

        return "redirect:/sede/" + id + "/asignar-servicios";
    }

    @GetMapping("/{id}/agregar-servicios")
    public String mostrarAgregarServicios(@PathVariable Long id, Model model) {
        Optional<Sede> sede = sedeRepository.findById(id);
        if (sede.isEmpty()) {
            return "redirect:/error";
        }
        List<ServicioComplementario> servicios = servicioComplementarioRepository.findAll();
        ServicioCostoForm servicioCostoForm = new ServicioCostoForm();
        servicioCostoForm.inicializarDesdeServicios(servicios);

        // NUEVO: obtener servicios ya asignados
        List<ServicioAsignadoDTO> serviciosAsignados = sedeServicioRepository.findServiciosBySedeId(id);

        model.addAttribute("sede", sede.get());
        model.addAttribute("servicios", servicios);
        model.addAttribute("servicioCostoForm", servicioCostoForm);
        model.addAttribute("serviciosAsignados", serviciosAsignados);

        return "agregar-servicios";
    }

    @GetMapping("/{id}/ver-servicios")
    public String verServiciosSede(@PathVariable Long id, Model model) {
        Optional<Sede> sede = sedeRepository.findById(id);
        if (sede.isEmpty()) {
            return "redirect:/error";
        }
        // Obtén los servicios asignados (usa tu DTO ServicioAsignadoDTO)
        List<ServicioAsignadoDTO> serviciosAsignados = sedeServicioRepository.findServiciosBySedeId(id);

        model.addAttribute("sede", sede.get());
        model.addAttribute("serviciosAsignados", serviciosAsignados);

        return "servicios-sede";
    }
}