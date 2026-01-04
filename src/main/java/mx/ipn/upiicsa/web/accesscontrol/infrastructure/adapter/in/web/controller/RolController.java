package mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.in.web.controller;

import mx.ipn.upiicsa.web.accesscontrol.application.port.in.RolService;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.in.web.dto.RolForm;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model.RolJpa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@Controller
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("roles", rolService.findAll());
        return "accesscontrol/roles/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("rolForm", new RolForm());
        return "accesscontrol/roles/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute RolForm form, RedirectAttributes redirectAttributes) {
        RolJpa rol = new RolJpa();
        rol.setId(form.getId());
        rol.setName(form.getName());
        rol.setDescription(form.getDescription());
        rol.setActive(form.getActive());

        rolService.save(rol);
        redirectAttributes.addFlashAttribute("message", "Rol guardado exitosamente");
        return "redirect:/roles/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable @NonNull Integer id, Model model) {
        return rolService.findById(id).map(r -> {
            RolForm form = new RolForm();
            form.setId(r.getId());
            form.setName(r.getName());
            form.setDescription(r.getDescription());
            form.setActive(r.getActive());
            model.addAttribute("rolForm", form);
            return "accesscontrol/roles/edit";
        }).orElse("redirect:/roles/list");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @NonNull Integer id, RedirectAttributes redirectAttributes) {
        rolService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Rol eliminado exitosamente");
        return "redirect:/roles/list";
    }
}
