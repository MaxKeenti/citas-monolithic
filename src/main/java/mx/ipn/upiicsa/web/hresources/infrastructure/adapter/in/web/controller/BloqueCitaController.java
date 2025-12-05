package mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.hresources.application.port.in.BloqueCitaService;
import mx.ipn.upiicsa.web.hresources.domain.BloqueCitaJpa;
import mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.dto.BloqueCitaForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hresources/bloque-cita")
@RequiredArgsConstructor
public class BloqueCitaController {
    private final BloqueCitaService bloqueCitaService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("bloquesCita", bloqueCitaService.findAll());
        return "hresources/bloque-cita/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("bloqueCitaForm", new BloqueCitaForm());
        return "hresources/bloque-cita/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute BloqueCitaForm form) {
        BloqueCitaJpa bc = new BloqueCitaJpa();
        bc.setIdSucursal(form.getIdSucursal());
        bc.setIdCita(form.getIdCita());
        bc.setFechaInicio(form.getFechaInicio());
        bc.setFechaFin(form.getFechaFin());
        bloqueCitaService.save(bc);
        return "redirect:/hresources/bloque-cita/list";
    }
}
