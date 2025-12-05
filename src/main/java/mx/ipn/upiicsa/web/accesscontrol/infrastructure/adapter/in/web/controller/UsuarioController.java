package mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.in.web.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model.PersonaJpa;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model.RolJpa;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model.UsuarioJpa;

import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.in.web.dto.UsuarioForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/usuarios")
@lombok.RequiredArgsConstructor
public class UsuarioController {
    private final mx.ipn.upiicsa.web.accesscontrol.application.port.in.UsuarioService usuarioService;
    private final mx.ipn.upiicsa.web.accesscontrol.application.port.in.PersonaService personaService;
    private final mx.ipn.upiicsa.web.accesscontrol.application.port.in.RolService rolService;

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("usuarioForm", new UsuarioForm());
        // personas that don't have usuario
        List<PersonaJpa> personas = personaService.findAll()
                .stream().filter(p -> p.getUsuario() == null).collect(Collectors.toList());
        List<RolJpa> roles = rolService.findAll().stream().filter(r -> Boolean.TRUE.equals(r.getActivo()))
                .collect(Collectors.toList());
        model.addAttribute("personas", personas);
        model.addAttribute("roles", roles);
        return "accesscontrol/usuarios/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("usuarioForm") UsuarioForm form, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("personas", personaService.findAll().stream().filter(p -> p.getUsuario() == null)
                    .collect(Collectors.toList()));
            model.addAttribute("roles", rolService.findAll().stream()
                    .filter(r -> Boolean.TRUE.equals(r.getActivo())).collect(Collectors.toList()));
            return "accesscontrol/usuarios/create";
        }
        UsuarioJpa u = new UsuarioJpa();
        u.setId(form.getIdPersona()); // id_usuario == id_persona
        u.setIdRol(form.getIdRol());
        u.setLogin(form.getLogin());
        u.setPassword(form.getPassword());
        u.setActivo(form.getActivo());
        usuarioService.save(u);
        return "redirect:/usuarios/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "accesscontrol/usuarios/list";
    }
}
