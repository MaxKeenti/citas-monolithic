package mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.in.web.controller;

import mx.ipn.upiicsa.web.accesscontrol.application.port.in.GeneroService;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.in.web.dto.GeneroForm;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model.GeneroJpa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("generos", generoService.findAll());
        return "accesscontrol/generos/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("generoForm", new GeneroForm());
        return "accesscontrol/generos/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute GeneroForm form, RedirectAttributes redirectAttributes) {
        GeneroJpa genero = new GeneroJpa();
        genero.setId(form.getId());
        genero.setName(form.getName());
        genero.setDescription(form.getDescription());
        genero.setActive(form.getActive());

        generoService.save(genero);
        redirectAttributes.addFlashAttribute("message", "Género guardado exitosamente");
        return "redirect:/generos/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        return generoService.findById(id).map(g -> {
            GeneroForm form = new GeneroForm();
            form.setId(g.getId());
            form.setName(g.getName());
            form.setDescription(g.getDescription());
            form.setActive(g.getActive());
            model.addAttribute("generoForm", form);
            return "accesscontrol/generos/edit";
        }).orElse("redirect:/generos/list");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        generoService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Género eliminado exitosamente");
        return "redirect:/generos/list";
    }
}
