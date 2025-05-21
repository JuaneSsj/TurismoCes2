package org.example.turismojdbc.controller;

import org.example.turismojdbc.model.Sede;
import org.example.turismojdbc.model.ServicioComplementario;
import org.example.turismojdbc.model.ServicioForm;
import org.example.turismojdbc.repository.SedeRepository;
import org.example.turismojdbc.repository.ServicioComplementarioRepository;
import org.example.turismojdbc.repository.SedeServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}/agregar-servicios")
    public String mostrarFormularioAgregarServicios(@PathVariable Long id, Model model) {
        Optional<Sede> sedeOpt = sedeRepository.findById(id);
        if (sedeOpt.isPresent()) {
            Sede sede = sedeOpt.get();
            model.addAttribute("sede", sede);
            model.addAttribute("servicioForm", new ServicioForm());

            List<ServicioComplementario> servicios = servicioComplementarioRepository.findAll();
            model.addAttribute("servicios", servicios);

            return "agregar-servicios";
        } else {
            return "redirect:/sede";
        }
    }



    @PostMapping("/{id}/agregar-servicios")
    public String agregarServiciosASede(@PathVariable Long id, @ModelAttribute("servicioForm") ServicioForm servicioForm) {
        List<Long> servicioIds = servicioForm.getServicioIds();
        if (servicioIds != null && !servicioIds.isEmpty()) {
            servicioIds.forEach(servicioId -> {
                sedeServicioRepository.asignarServicioASede(id, servicioId);
            });
        }
        return "redirect:/sede/" + id;
    }
}