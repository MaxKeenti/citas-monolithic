package mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.hresources.application.port.in.EmpleadoHorarioService;
import mx.ipn.upiicsa.web.hresources.domain.EmpleadoHorarioJpa;
import mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.dto.EmpleadoHorarioForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hresources/empleado-horario")
@RequiredArgsConstructor
public class EmpleadoHorarioController {
    private final EmpleadoHorarioService empleadoHorarioService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("empleadoHorarios", empleadoHorarioService.findAll());
        return "hresources/empleado-horario/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("empleadoHorarioForm", new EmpleadoHorarioForm());
        return "hresources/empleado-horario/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute EmpleadoHorarioForm form) {
        EmpleadoHorarioJpa eh = new EmpleadoHorarioJpa();
        eh.setIdHorario(form.getIdHorario());
        eh.setIdEmpleado(form.getIdEmpleado());
        empleadoHorarioService.save(eh);
        return "redirect:/hresources/empleado-horario/list";
    }
}
