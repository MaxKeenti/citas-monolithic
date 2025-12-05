package mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.hresources.application.port.in.DiaLaboralService;
import mx.ipn.upiicsa.web.hresources.domain.DiaLaboralJpa;
import mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.dto.DiaLaboralForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hresources/dia-laboral")
@RequiredArgsConstructor
public class DiaLaboralController {
    private final DiaLaboralService diaLaboralService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("diasLaborales", diaLaboralService.findAll());
        return "hresources/dia-laboral/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("diaLaboralForm", new DiaLaboralForm());
        return "hresources/dia-laboral/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute DiaLaboralForm form) {
        DiaLaboralJpa dia = new DiaLaboralJpa();
        dia.setNombre(form.getNombre());
        dia.setDescripcion(form.getDescripcion());
        dia.setActivo(form.getActivo());
        diaLaboralService.save(dia);
        return "redirect:/hresources/dia-laboral/list";
    }
}
