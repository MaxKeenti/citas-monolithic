package mx.ipn.upiicsa.web.catalog.mvc.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.catalog.jpa.model.ServicioJpa;
import mx.ipn.upiicsa.web.catalog.jpa.repository.ServicioJpaRepository;
import mx.ipn.upiicsa.web.catalog.mvc.dto.ServicioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/servicios")
public class ServicioController {
    @Autowired
    private ServicioJpaRepository servicioJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("servicioForm", new ServicioForm());
        return "catalog/servicios/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("servicioForm") ServicioForm form, BindingResult br, Model model){
        if(br.hasErrors()){
            return "catalog/servicios/create";
        }
        ServicioJpa s = new ServicioJpa();
        s.setNombre(form.getNombre());
        s.setDescripcion(form.getDescripcion());
        s.setNuDuracion(form.getDuracion());
        s.setStActivo(form.getActivo());
        servicioJpaRepository.save(s);
        return "redirect:/servicios/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<ServicioJpa> servicios = servicioJpaRepository.findAll();
        model.addAttribute("servicios", servicios);
        return "catalog/servicios/list";
    }
}
