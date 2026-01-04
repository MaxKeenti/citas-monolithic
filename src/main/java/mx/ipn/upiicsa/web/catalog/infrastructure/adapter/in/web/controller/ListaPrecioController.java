package mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.controller;

import mx.ipn.upiicsa.web.catalog.application.port.in.EstadoListaPrecioService;
import mx.ipn.upiicsa.web.catalog.application.port.in.ListaPrecioService;
import mx.ipn.upiicsa.web.catalog.domain.ListaPrecioJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.lang.NonNull;

@Controller
@RequestMapping("/listas-precio")
public class ListaPrecioController {

    @Autowired
    private ListaPrecioService listaPrecioService;

    @Autowired
    private EstadoListaPrecioService estadoListaPrecioService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("listasPrecio", listaPrecioService.findAll());
        return "catalog/listas-precio/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("listaPrecio",
                new mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.dto.ListaPrecioForm());
        model.addAttribute("estados", estadoListaPrecioService.findAll());
        return "catalog/listas-precio/create";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("listaPrecio") mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.dto.ListaPrecioForm form,
            RedirectAttributes redirectAttributes) {
        ListaPrecioJpa listaPrecio = new ListaPrecioJpa();
        if (form.getId() != null) {
            listaPrecio = listaPrecioService.findById(form.getId()).orElse(new ListaPrecioJpa());
        }

        listaPrecio.setName(form.getName());
        listaPrecio.setStartDate(form.getStartDate());
        listaPrecio.setEndDate(form.getEndDate());
        listaPrecio.setActive(form.getActive());

        mx.ipn.upiicsa.web.catalog.domain.EstadoListaPrecioJpa estado = new mx.ipn.upiicsa.web.catalog.domain.EstadoListaPrecioJpa();
        estado.setId(form.getStatusId());
        listaPrecio.setStatus(estado);

        listaPrecioService.save(listaPrecio);
        redirectAttributes.addFlashAttribute("message", "Lista de precio guardada exitosamente");
        return "redirect:/listas-precio/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable @NonNull Integer id, Model model) {
        return listaPrecioService.findById(id).map(lp -> {
            mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.dto.ListaPrecioForm form = new mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.dto.ListaPrecioForm();
            form.setId(lp.getId());
            form.setName(lp.getName());
            if (lp.getStatus() != null) {
                form.setStatusId(lp.getStatus().getId());
            }
            form.setStartDate(lp.getStartDate());
            form.setEndDate(lp.getEndDate());
            form.setActive(lp.getActive());

            model.addAttribute("listaPrecio", form);
            model.addAttribute("estados", estadoListaPrecioService.findAll());
            return "catalog/listas-precio/edit";
        }).orElse("redirect:/listas-precio/list");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @NonNull Integer id, RedirectAttributes redirectAttributes) {
        listaPrecioService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Lista de precio eliminada exitosamente");
        return "redirect:/listas-precio/list";
    }
}
