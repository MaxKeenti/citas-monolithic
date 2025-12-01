package mx.ipn.upiicsa.web.appointment.mvc.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.appointment.jpa.model.CitaJpa;
import mx.ipn.upiicsa.web.appointment.jpa.repository.CitaJpaRepository;

import mx.ipn.upiicsa.web.catalog.jpa.repository.ServicioJpaRepository;
import mx.ipn.upiicsa.web.catalog.jpa.repository.ServicioListaPrecioJpaRepository;
import mx.ipn.upiicsa.web.catalog.jpa.model.ServicioListaPrecioJpa;
import mx.ipn.upiicsa.web.catalog.jpa.repository.ListaPrecioJpaRepository;
import mx.ipn.upiicsa.web.hresources.jpa.repository.SucursalJpaRepository;
import mx.ipn.upiicsa.web.hresources.jpa.repository.EmpleadoJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.PersonaJpaRepository;
import mx.ipn.upiicsa.web.appointment.mvc.dto.CitaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/citas")
public class CitaController {
    @Autowired
    private CitaJpaRepository citaJpaRepository;
    @Autowired
    private PersonaJpaRepository personaJpaRepository;
    @Autowired
    private ServicioJpaRepository servicioJpaRepository;
    @Autowired
    private ServicioListaPrecioJpaRepository servicioListaPrecioJpaRepository;
    @Autowired
    private ListaPrecioJpaRepository listaPrecioJpaRepository;
    @Autowired
    private SucursalJpaRepository sucursalJpaRepository;
    @Autowired
    private EmpleadoJpaRepository empleadoJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("citaForm", new CitaForm());
        model.addAttribute("personas", personaJpaRepository.findAll());
        model.addAttribute("servicios", servicioJpaRepository.findAll());
        model.addAttribute("sucursales", sucursalJpaRepository.findAll());
        model.addAttribute("empleados", empleadoJpaRepository.findAll());
        // initially no lista precios loaded
        model.addAttribute("listas", List.of());
        return "appointment/citas/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("citaForm") CitaForm form, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("personas", personaJpaRepository.findAll());
            model.addAttribute("servicios", servicioJpaRepository.findAll());
            model.addAttribute("sucursales", sucursalJpaRepository.findAll());
            model.addAttribute("empleados", empleadoJpaRepository.findAll());
            model.addAttribute("listas", servicioListaPrecioJpaRepository.findByFkIdServicio(form.getIdServicio()).stream().map(sp -> listaPrecioJpaRepository.findById(sp.getFkIdListaPrecio()).orElse(null)).filter(lp -> lp!=null).collect(Collectors.toList()));
            return "appointment/citas/create";
        }
        CitaJpa c = new CitaJpa();
        c.setFkIdPersona(form.getIdPersona());
        c.setFkIdServicio(form.getIdServicio());
        c.setFkIdListaPrecio(form.getIdListaPrecio());
        c.setFkIdSucursal(form.getIdSucursal());
        c.setFkIdEmpleado(form.getIdEmpleado());
        citaJpaRepository.save(c);
        return "redirect:/citas/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("citas", citaJpaRepository.findAll());
        return "appointment/citas/list";
    }

    // Endpoint to fetch active listas filtered by servicio (used by AJAX from the form)
    @GetMapping(path = "/listas-by-servicio", produces = "application/json")
    @ResponseBody
    public List<?> listasByServicio(@RequestParam Integer servicioId){
        // find servicio-lista entries for this servicio
        List<ServicioListaPrecioJpa> sps = servicioListaPrecioJpaRepository.findByFkIdServicio(servicioId);
        // for each, load listaPrecio and filter by estado activo (we assume estado id for active is known or Estado has 'st_activo')
        return sps.stream().map(sp -> listaPrecioJpaRepository.findById(sp.getFkIdListaPrecio()).orElse(null)).filter(lp -> lp != null && Boolean.TRUE.equals(lp.getStActivo())).collect(Collectors.toList());
    }
}
