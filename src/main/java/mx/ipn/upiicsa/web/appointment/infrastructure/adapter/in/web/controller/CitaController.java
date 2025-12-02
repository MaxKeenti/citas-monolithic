package mx.ipn.upiicsa.web.appointment.infrastructure.adapter.in.web.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.appointment.domain.CitaJpa;
import mx.ipn.upiicsa.web.appointment.application.port.in.CitaService;

import mx.ipn.upiicsa.web.catalog.application.port.out.ServicioJpaRepository;
import mx.ipn.upiicsa.web.catalog.application.port.out.ServicioListaPrecioJpaRepository;
import mx.ipn.upiicsa.web.catalog.domain.ServicioListaPrecioJpa;
import mx.ipn.upiicsa.web.catalog.application.port.out.ListaPrecioJpaRepository;
import mx.ipn.upiicsa.web.hresources.application.port.out.SucursalJpaRepository;
import mx.ipn.upiicsa.web.hresources.application.port.out.EmpleadoJpaRepository;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.repository.PersonaJpaRepository;
import mx.ipn.upiicsa.web.appointment.infrastructure.adapter.in.web.dto.CitaForm;

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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/citas")
@lombok.RequiredArgsConstructor
public class CitaController {
    private final CitaService citaService;
    private final PersonaJpaRepository personaJpaRepository;
    private final ServicioJpaRepository servicioJpaRepository;
    private final ServicioListaPrecioJpaRepository servicioListaPrecioJpaRepository;
    private final ListaPrecioJpaRepository listaPrecioJpaRepository;
    private final SucursalJpaRepository sucursalJpaRepository;
    private final EmpleadoJpaRepository empleadoJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model) {
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
    public String create(@Valid @ModelAttribute("citaForm") CitaForm form, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("personas", personaJpaRepository.findAll());
            model.addAttribute("servicios", servicioJpaRepository.findAll());
            model.addAttribute("sucursales", sucursalJpaRepository.findAll());
            model.addAttribute("empleados", empleadoJpaRepository.findAll());
            if (form.getIdServicio() != null) {
                model.addAttribute("listas",
                        servicioListaPrecioJpaRepository.findByFkIdServicio(form.getIdServicio()).stream()
                                .map(ServicioListaPrecioJpa::getFkIdListaPrecio)
                                .filter(Objects::nonNull)
                                .map(listaPrecioJpaRepository::findById)
                                .flatMap(Optional::stream)
                                .filter(lp -> Boolean.TRUE.equals(lp.getStActivo()))
                                .collect(Collectors.toList()));
            } else {
                model.addAttribute("listas", List.of());
            }
            return "appointment/citas/create";
        }
        CitaJpa c = new CitaJpa();
        c.setFkIdPersona(form.getIdPersona());
        c.setFkIdServicio(form.getIdServicio());
        c.setFkIdListaPrecio(form.getIdListaPrecio());
        c.setFkIdSucursal(form.getIdSucursal());
        c.setFkIdEmpleado(form.getIdEmpleado());
        c.setFechaHora(form.getFechaHora());
        citaService.save(c);
        return "redirect:/citas/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("citas", citaService.findAll());
        return "appointment/citas/list";
    }

    // Endpoint to fetch active listas filtered by servicio (used by AJAX from the
    // form)
    @GetMapping(path = "/listas-by-servicio", produces = "application/json")
    @ResponseBody
    public List<?> listasByServicio(@RequestParam Integer servicioId) {
        // find servicio-lista entries for this servicio
        List<ServicioListaPrecioJpa> sps = servicioListaPrecioJpaRepository.findByFkIdServicio(servicioId);
        // for each, load listaPrecio and filter by estado activo (we assume estado id
        // for active is known or Estado has 'st_activo')
        return sps.stream()
                .map(ServicioListaPrecioJpa::getFkIdListaPrecio)
                .filter(Objects::nonNull)
                .map(listaPrecioJpaRepository::findById)
                .flatMap(Optional::stream)
                .filter(lp -> Boolean.TRUE.equals(lp.getStActivo()))
                .collect(Collectors.toList());
    }
}
