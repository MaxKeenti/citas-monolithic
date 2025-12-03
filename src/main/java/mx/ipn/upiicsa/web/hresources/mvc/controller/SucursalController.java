package mx.ipn.upiicsa.web.hresources.mvc.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.hresources.jpa.model.EstablecimientoJpa;
import mx.ipn.upiicsa.web.hresources.jpa.model.SucursalJpa;
import mx.ipn.upiicsa.web.hresources.jpa.repository.EstablecimientoJpaRepository;
import mx.ipn.upiicsa.web.hresources.jpa.repository.SucursalJpaRepository;
import mx.ipn.upiicsa.web.hresources.mvc.dto.SucursalForm;
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
@RequestMapping("/sucursales")
public class SucursalController {
    @Autowired
    private SucursalJpaRepository sucursalJpaRepository;
    @Autowired
    private EstablecimientoJpaRepository establecimientoJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("sucursalForm", new SucursalForm());
        List<EstablecimientoJpa> establecimientos = establecimientoJpaRepository.findAll();
        model.addAttribute("establecimientos", establecimientos);
        return "hresources/sucursales/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("sucursalForm") SucursalForm form, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("establecimientos", establecimientoJpaRepository.findAll());
            return "hresources/sucursales/create";
        }
        SucursalJpa s = new SucursalJpa();
        s.setFkIdEstablecimiento(form.getIdEstablecimiento());
        s.setTxNombre(form.getNombre());
        s.setGmUbicacion(null); // placeholder, store null or implement later
        sucursalJpaRepository.save(s);
        return "redirect:/sucursales/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("sucursales", sucursalJpaRepository.findAll());
        return "hresources/sucursales/list";
    }
}
