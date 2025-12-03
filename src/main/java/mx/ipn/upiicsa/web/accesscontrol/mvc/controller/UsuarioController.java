package mx.ipn.upiicsa.web.accesscontrol.mvc.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.accesscontrol.jpa.repository.RolJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.UsuarioJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.PersonaJpaRepository;
import mx.ipn.upiicsa.web.accesscontrol.mvc.dto.UsuarioForm;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.PersonaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.RolJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.UsuarioJpa;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UsuarioController {
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;
    @Autowired
    private PersonaJpaRepository personaJpaRepository;
    @Autowired
    private RolJpaRepository rolJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("usuarioForm", new UsuarioForm());
        // personas that don't have usuario
        List<PersonaJpa> personas = personaJpaRepository.findAll()
                .stream().filter(p -> p.getUsuario() == null).collect(Collectors.toList());
        List<RolJpa> roles = rolJpaRepository.findAll().stream().filter(r -> Boolean.TRUE.equals(r.getActivo())).collect(Collectors.toList());
        model.addAttribute("personas", personas);
        model.addAttribute("roles", roles);
        return "accesscontrol/usuarios/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("usuarioForm") UsuarioForm form, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("personas", personaJpaRepository.findAll().stream().filter(p -> p.getUsuario()==null).collect(Collectors.toList()));
            model.addAttribute("roles", rolJpaRepository.findAll().stream().filter(r -> Boolean.TRUE.equals(r.getActivo())).collect(Collectors.toList()));
            return "accesscontrol/usuarios/create";
        }
        UsuarioJpa u = new UsuarioJpa();
        u.setId(form.getIdPersona()); // id_usuario == id_persona
        u.setIdRol(form.getIdRol());
        u.setLogin(form.getLogin());
        u.setPassword(form.getPassword());
        u.setActivo(form.getActivo());
        usuarioJpaRepository.save(u);
        return "redirect:/usuarios/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("usuarios", usuarioJpaRepository.findAll());
        return "accesscontrol/usuarios/list";
    }
}
