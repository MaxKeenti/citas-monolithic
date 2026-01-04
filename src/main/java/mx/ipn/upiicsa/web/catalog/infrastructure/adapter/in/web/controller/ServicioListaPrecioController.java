package mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.controller;

import mx.ipn.upiicsa.web.catalog.application.port.in.ListaPrecioService;
import mx.ipn.upiicsa.web.catalog.application.port.in.ServicioListaPrecioService;
import mx.ipn.upiicsa.web.catalog.application.port.in.ServicioService;
import mx.ipn.upiicsa.web.catalog.domain.ServicioListaPrecioJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicio-lista-precios")
public class ServicioListaPrecioController {

    @Autowired
    private ServicioListaPrecioService servicioListaPrecioService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ListaPrecioService listaPrecioService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("precios", servicioListaPrecioService.findAll());
        return "catalog/servicio-lista-precios/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("precio",
                new mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.dto.ServicioListaPrecioForm());
        model.addAttribute("servicios", servicioService.findAll());
        model.addAttribute("listas", listaPrecioService.findAll());
        return "catalog/servicio-lista-precios/create";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("precio") mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.dto.ServicioListaPrecioForm form,
            RedirectAttributes redirectAttributes) {
        ServicioListaPrecioJpa precio = new ServicioListaPrecioJpa();
        if (form.getId() != null) {
            precio = servicioListaPrecioService.findById(form.getId()).orElse(new ServicioListaPrecioJpa());
        }

        mx.ipn.upiicsa.web.catalog.domain.ServicioJpa servicio = new mx.ipn.upiicsa.web.catalog.domain.ServicioJpa();
        servicio.setId(form.getServiceId());
        precio.setService(servicio);

        mx.ipn.upiicsa.web.catalog.domain.ListaPrecioJpa lista = new mx.ipn.upiicsa.web.catalog.domain.ListaPrecioJpa();
        lista.setId(form.getPriceListId());
        precio.setPriceList(lista);

        precio.setPrice(form.getPrice());

        servicioListaPrecioService.save(precio);
        redirectAttributes.addFlashAttribute("message", "Precio guardado exitosamente");
        return "redirect:/servicio-lista-precios/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        return servicioListaPrecioService.findById(id).map(p -> {
            mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.dto.ServicioListaPrecioForm form = new mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.dto.ServicioListaPrecioForm();
            form.setId(p.getId());
            if (p.getService() != null)
                form.setServiceId(p.getService().getId());
            if (p.getPriceList() != null)
                form.setPriceListId(p.getPriceList().getId());
            form.setPrice(p.getPrice());

            model.addAttribute("precio", form);
            model.addAttribute("servicios", servicioService.findAll());
            model.addAttribute("listas", listaPrecioService.findAll());
            return "catalog/servicio-lista-precios/edit";
        }).orElse("redirect:/servicio-lista-precios/list");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        servicioListaPrecioService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Precio eliminado exitosamente");
        return "redirect:/servicio-lista-precios/list";
    }
}
