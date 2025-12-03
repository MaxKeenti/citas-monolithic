package mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model.PersonaJpa;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.repository.PersonaJpaRepository;
import mx.ipn.upiicsa.web.hresources.domain.EmpleadoJpa;
import mx.ipn.upiicsa.web.hresources.domain.SucursalJpa;
import mx.ipn.upiicsa.web.hresources.application.port.out.EmpleadoJpaRepository;
import mx.ipn.upiicsa.web.hresources.application.port.out.SucursalJpaRepository;
import mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.dto.EmpleadoForm;
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
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoJpaRepository empleadoJpaRepository;
    @Autowired
    private PersonaJpaRepository personaJpaRepository;
    @Autowired
    private SucursalJpaRepository sucursalJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("empleadoForm", new EmpleadoForm());
        // personas that are not yet employees (simple approach: list all personas and filter by existence of empleado)
        List<PersonaJpa> personas = personaJpaRepository.findAll();
        // leave filtering to user for now
        List<SucursalJpa> sucursales = sucursalJpaRepository.findAll();
        model.addAttribute("personas", personas);
        model.addAttribute("sucursales", sucursales);
        return "hresources/empleados/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("empleadoForm") EmpleadoForm form, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("personas", personaJpaRepository.findAll());
            model.addAttribute("sucursales", sucursalJpaRepository.findAll());
            return "hresources/empleados/create";
        }
        EmpleadoJpa e = new EmpleadoJpa();
        e.setIdEmpleado(form.getIdPersona()); // employee id equals persona id
        e.setFkIdSucursal(form.getIdSucursal());
        empleadoJpaRepository.save(e);
        return "redirect:/empleados/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("empleados", empleadoJpaRepository.findAll());
        return "hresources/empleados/list";
    }
}
