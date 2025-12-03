package mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.hresources.domain.EstablecimientoJpa;
import mx.ipn.upiicsa.web.hresources.domain.SucursalJpa;
import mx.ipn.upiicsa.web.hresources.application.port.out.EstablecimientoJpaRepository;
import mx.ipn.upiicsa.web.hresources.application.port.in.SucursalService;
import mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.dto.SucursalForm;

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
@lombok.RequiredArgsConstructor
public class SucursalController {
    private final SucursalService sucursalService;
    private final EstablecimientoJpaRepository establecimientoJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("sucursalForm", new SucursalForm());
        List<EstablecimientoJpa> establecimientos = establecimientoJpaRepository.findAll();
        model.addAttribute("establecimientos", establecimientos);
        return "hresources/sucursales/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("sucursalForm") SucursalForm form, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("establecimientos", establecimientoJpaRepository.findAll());
            return "hresources/sucursales/create";
        }
        SucursalJpa s = new SucursalJpa();
        s.setFkIdEstablecimiento(form.getIdEstablecimiento());
        s.setTxNombre(form.getNombre());

        org.locationtech.jts.geom.GeometryFactory gf = new org.locationtech.jts.geom.GeometryFactory();
        org.locationtech.jts.geom.Point point = gf
                .createPoint(new org.locationtech.jts.geom.Coordinate(form.getLongitud(), form.getLatitud()));
        point.setSRID(4326);
        s.setGmUbicacion(point);

        sucursalService.save(s);
        return "redirect:/sucursales/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("sucursales", sucursalService.findAll());
        return "hresources/sucursales/list";
    }
}
