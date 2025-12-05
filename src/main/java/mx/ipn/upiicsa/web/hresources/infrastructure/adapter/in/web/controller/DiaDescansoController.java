package mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.hresources.application.port.in.DiaDescansoService;
import mx.ipn.upiicsa.web.hresources.domain.DiaDescansoJpa;
import mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.dto.DiaDescansoForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hresources/dia-descanso")
@RequiredArgsConstructor
public class DiaDescansoController {
    private final DiaDescansoService diaDescansoService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("diasDescanso", diaDescansoService.findAll());
        return "hresources/dia-descanso/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("diaDescansoForm", new DiaDescansoForm());
        return "hresources/dia-descanso/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute DiaDescansoForm form) {
        DiaDescansoJpa dia = new DiaDescansoJpa();
        dia.setIdEmpleado(form.getIdEmpleado());
        dia.setFechaDescanso(form.getFechaDescanso());
        diaDescansoService.save(dia);
        return "redirect:/hresources/dia-descanso/list";
    }
}
