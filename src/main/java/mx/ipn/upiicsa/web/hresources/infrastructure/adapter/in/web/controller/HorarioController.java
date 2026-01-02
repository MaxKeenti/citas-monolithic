package mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.hresources.application.port.in.HorarioService;
import mx.ipn.upiicsa.web.hresources.domain.HorarioJpa;
import mx.ipn.upiicsa.web.hresources.infrastructure.adapter.in.web.dto.HorarioForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hresources/horario")
@RequiredArgsConstructor
public class HorarioController {
    private final HorarioService horarioService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("horarios", horarioService.findAll());
        return "hresources/horario/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("horarioForm", new HorarioForm());
        return "hresources/horario/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute HorarioForm form) {
        HorarioJpa horario = new HorarioJpa();
        saveHorario(horario, form);
        return "redirect:/hresources/horario/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        return horarioService.findById(id)
                .map(horario -> {
                    HorarioForm form = new HorarioForm();
                    form.setId(horario.getId());
                    form.setBranchId(horario.getBranchId());
                    form.setDayId(horario.getDayId());
                    form.setStartTime(horario.getStartTime());
                    form.setEndTime(horario.getEndTime());
                    model.addAttribute("horarioForm", form);
                    return "hresources/horario/edit";
                })
                .orElse("redirect:/hresources/horario/list");
    }

    @PostMapping("/update")
    public String update(@ModelAttribute HorarioForm form) {
        HorarioJpa horario = new HorarioJpa();
        horario.setId(form.getId());
        saveHorario(horario, form);
        return "redirect:/hresources/horario/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteConfirmation(@PathVariable Integer id, Model model) {
        return horarioService.findById(id)
                .map(horario -> {
                    model.addAttribute("horario", horario);
                    return "hresources/horario/delete";
                })
                .orElse("redirect:/hresources/horario/list");
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Integer id) {
        horarioService.deleteById(id);
        return "redirect:/hresources/horario/list";
    }

    private void saveHorario(HorarioJpa horario, HorarioForm form) {
        horario.setBranchId(form.getBranchId());
        horario.setDayId(form.getDayId());
        horario.setStartTime(form.getStartTime());
        horario.setEndTime(form.getEndTime());
        horarioService.save(horario);
    }
}
