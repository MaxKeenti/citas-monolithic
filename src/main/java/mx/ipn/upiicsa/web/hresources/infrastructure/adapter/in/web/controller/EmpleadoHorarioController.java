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
        saveEmpleadoHorario(eh, form);
        return "redirect:/hresources/empleado-horario/list";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam Integer scheduleId, @RequestParam Integer employeeId, Model model) {
        mx.ipn.upiicsa.web.hresources.domain.EmpleadoHorarioId id = new mx.ipn.upiicsa.web.hresources.domain.EmpleadoHorarioId();
        id.setScheduleId(scheduleId);
        id.setEmployeeId(employeeId);

        return empleadoHorarioService.findById(id)
                .map(eh -> {
                    EmpleadoHorarioForm form = new EmpleadoHorarioForm();
                    form.setScheduleId(eh.getScheduleId());
                    form.setEmployeeId(eh.getEmployeeId());
                    model.addAttribute("empleadoHorarioForm", form);
                    return "hresources/empleado-horario/edit";
                })
                .orElse("redirect:/hresources/empleado-horario/list");
    }

    @PostMapping("/update")
    public String update(@ModelAttribute EmpleadoHorarioForm form) {
        EmpleadoHorarioJpa eh = new EmpleadoHorarioJpa();
        saveEmpleadoHorario(eh, form);
        return "redirect:/hresources/empleado-horario/list";
    }

    @GetMapping("/delete")
    public String deleteConfirmation(@RequestParam Integer scheduleId, @RequestParam Integer employeeId, Model model) {
        mx.ipn.upiicsa.web.hresources.domain.EmpleadoHorarioId id = new mx.ipn.upiicsa.web.hresources.domain.EmpleadoHorarioId();
        id.setScheduleId(scheduleId);
        id.setEmployeeId(employeeId);

        return empleadoHorarioService.findById(id)
                .map(eh -> {
                    model.addAttribute("empleadoHorario", eh);
                    return "hresources/empleado-horario/delete";
                })
                .orElse("redirect:/hresources/empleado-horario/list");
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Integer scheduleId, @RequestParam Integer employeeId) {
        mx.ipn.upiicsa.web.hresources.domain.EmpleadoHorarioId id = new mx.ipn.upiicsa.web.hresources.domain.EmpleadoHorarioId();
        id.setScheduleId(scheduleId);
        id.setEmployeeId(employeeId);

        empleadoHorarioService.deleteById(id);
        return "redirect:/hresources/empleado-horario/list";
    }

    private void saveEmpleadoHorario(EmpleadoHorarioJpa eh, EmpleadoHorarioForm form) {
        eh.setScheduleId(form.getScheduleId());
        eh.setEmployeeId(form.getEmployeeId());
        empleadoHorarioService.save(eh);
    }
}
