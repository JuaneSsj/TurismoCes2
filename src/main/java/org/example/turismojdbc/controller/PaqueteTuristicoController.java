package org.example.turismojdbc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.turismojdbc.model.PaqueteTuristico;
import org.example.turismojdbc.model.enums.DuracionPaquete;
import org.example.turismojdbc.repository.AgenciaRepository;
import org.example.turismojdbc.repository.PaqueteTuristicoRepository;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paquete")
public class PaqueteTuristicoController {

    private final PaqueteTuristicoRepository paqueteRepo;
    private final AgenciaRepository agenciaRepo;

    public PaqueteTuristicoController(PaqueteTuristicoRepository paqueteRepo, AgenciaRepository agenciaRepo) {
        this.paqueteRepo = paqueteRepo;
        this.agenciaRepo = agenciaRepo;
    }

    // Mostrar formulario para nuevo paquete relacionado a una agencia
    @GetMapping("/nuevo/{agenciaId}")
    public String mostrarFormularioPaquete(@PathVariable Long agenciaId, Model model) {
        PaqueteTuristico paquete = new PaqueteTuristico();
        paquete.setAgenciaId(AggregateReference.to(agenciaId));

        model.addAttribute("paquete", paquete);
        model.addAttribute("duraciones", DuracionPaquete.values());
        return "formulario-paquete";
    }

    @PostMapping("/guardar")
    public String guardarPaquete(
            @Valid @ModelAttribute("paquete") PaqueteTuristico paquete,
            BindingResult result,
            @RequestParam("agenciaIdRaw") Long agenciaIdRaw,
            Model model
    ) {
        paquete.setAgenciaId(AggregateReference.to(agenciaIdRaw));

        if (result.hasErrors()) {
            model.addAttribute("duraciones", DuracionPaquete.values());
            return "formulario-paquete";
        }

        paqueteRepo.save(paquete);
        return "redirect:/agencia/lista";
    }
}