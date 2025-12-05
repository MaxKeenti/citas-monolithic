package mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.catalog.application.port.in.EstadoListaPrecioService;
import mx.ipn.upiicsa.web.catalog.domain.EstadoListaPrecioJpa;
import mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.dto.EstadoListaPrecioForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/catalog/estado-lista-precio")
@RequiredArgsConstructor
public class EstadoListaPrecioController {
    private final EstadoListaPrecioService estadoListaPrecioService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("estados", estadoListaPrecioService.findAll());
        return "catalog/estado-lista-precio/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("estadoListaPrecioForm", new EstadoListaPrecioForm());
        return "catalog/estado-lista-precio/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute EstadoListaPrecioForm form) {
        EstadoListaPrecioJpa estado = new EstadoListaPrecioJpa();
        estado.setNombre(form.getNombre());
        estadoListaPrecioService.save(estado);
        return "redirect:/catalog/estado-lista-precio/list";
    }
}
