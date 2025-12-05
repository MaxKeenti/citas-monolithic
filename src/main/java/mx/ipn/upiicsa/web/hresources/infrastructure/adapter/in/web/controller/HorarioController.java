package mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.hresources.application.port.in.HorarioService;
import mx.ipn.upiicsa.web.hresources.domain.HorarioJpa;
import mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.dto.HorarioForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hresources/horario")
@RequiredArgsConstructor
public class HorarioController {
    private final HorarioService horarioService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("horarios", horarioService.findAll());
        return "hresources/horario/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("horarioForm", new HorarioForm());
        return "hresources/horario/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute HorarioForm form) {
        HorarioJpa horario = new HorarioJpa();
        horario.setIdSucursal(form.getIdSucursal());
        horario.setIdDia(form.getIdDia());
        horario.setHoraInicio(form.getHoraInicio());
        horario.setHoraFin(form.getHoraFin());
        horarioService.save(horario);
        return "redirect:/hresources/horario/list";
    }
}
