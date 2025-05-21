package org.example.turismojdbc.controller;

import org.example.turismojdbc.AgenciaConSedeDTO;
import org.example.turismojdbc.model.AgenciaTurismo;
import org.example.turismojdbc.model.Sede;
import org.example.turismojdbc.repository.AgenciaRepository;
import org.example.turismojdbc.repository.PaqueteTuristicoRepository;
import org.example.turismojdbc.repository.SedeRepository;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/agencia")
public class AgenciaController {

    private final AgenciaRepository agenciaRepo;
    private final SedeRepository sedeRepo;
    private final PaqueteTuristicoRepository paqueteRepo;

    public AgenciaController(AgenciaRepository agenciaRepo, SedeRepository sedeRepo, PaqueteTuristicoRepository paqueteRepo) {
        this.agenciaRepo = agenciaRepo;
        this.sedeRepo = sedeRepo;
        this.paqueteRepo = paqueteRepo;
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("agencia", new AgenciaTurismo());
        model.addAttribute("sede", new Sede());
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardarAgenciaYSeSede(
            @ModelAttribute AgenciaTurismo agencia,
            @ModelAttribute Sede sede) {

        AgenciaTurismo agenciaGuardada = agenciaRepo.save(agencia);
        sede.setAgenciaId(AggregateReference.to(agenciaGuardada.getId()));
        sedeRepo.save(sede);

        return "redirect:/agencia/lista";
    }

    @GetMapping("/lista")
    public String listarAgencias(Model model) {
        Iterable<AgenciaTurismo> agencias = agenciaRepo.findAll();

        List<AgenciaConSedeDTO> agenciasConSede = new ArrayList<>();

        for (AgenciaTurismo agencia : agencias) {
            Optional<Sede> sedeOpt = sedeRepo.findByAgenciaId(agencia.getId());
            Sede sede = sedeOpt.orElse(null); // puede ser null si no tiene sede
            agenciasConSede.add(new AgenciaConSedeDTO(agencia, sede));
        }

        model.addAttribute("agenciasConSede", agenciasConSede);
        return "lista";
    }

    @GetMapping("/{id}/paquetes")
    public String mostrarPaquetesPorAgencia(@PathVariable Long id, Model model) {
        var agencia = agenciaRepo.findById(id);
        var paquetes = paqueteRepo.findAllByAgenciaId(id);

        model.addAttribute("agencia", agencia.orElse(null));
        model.addAttribute("paquetes", paquetes);

        return "paquetes-agencia";
    }

    @GetMapping("/{id}/nueva-sede")
    public String mostrarFormularioNuevaSede(@PathVariable Long id, Model model) {
        // Verificar si la agencia existe
        Optional<AgenciaTurismo> agenciaOpt = agenciaRepo.findById(id);
        if (agenciaOpt.isEmpty()) {
            // Manejar el caso en que la agencia no exista (por ejemplo, redirigir a una página de error)
            return "redirect:/agencia/lista"; // O una página de error
        }

        // Agregar la agencia al modelo para usarla en el formulario (si es necesario)
        model.addAttribute("agencia", agenciaOpt.get());

        // Crear un objeto Sede vacío para el formulario
        model.addAttribute("sede", new Sede());

        // Retornar la vista del formulario para agregar una nueva sede
        return "nueva-sede";
    }
}