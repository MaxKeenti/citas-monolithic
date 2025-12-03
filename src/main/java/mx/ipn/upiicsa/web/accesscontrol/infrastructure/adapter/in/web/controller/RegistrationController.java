package mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.in.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.in.web.dto.RegistrationForm;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model.PersonaJpa;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model.RolJpa;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model.UsuarioJpa;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.repository.GeneroJpaRepository;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.repository.PersonaJpaRepository;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.repository.RolJpaRepository;
import mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final PersonaJpaRepository personaJpaRepository;
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final RolJpaRepository rolJpaRepository;
    private final GeneroJpaRepository generoJpaRepository;

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        model.addAttribute("generos", generoJpaRepository.findAll());
        return "accesscontrol/login/register";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("registrationForm") RegistrationForm form, BindingResult br,
            Model model) {
        if (br.hasErrors()) {
            model.addAttribute("generos", generoJpaRepository.findAll());
            return "accesscontrol/login/register";
        }

        // 1. Create Persona
        PersonaJpa p = new PersonaJpa();
        p.setIdGenero(form.getIdGenero());
        p.setNombre(form.getNombre());
        p.setPrimerApellido(form.getPrimerApellido());
        p.setSegundoApellido(form.getSegundoApellido());
        p.setFechaNacimiento(form.getFechaNacimiento());
        p = personaJpaRepository.save(p);

        // 2. Find Role 'cliente'
        RolJpa rolCliente = rolJpaRepository.findById(3)
                .orElseThrow(() -> new RuntimeException("Role 'cliente' not found"));

        // 3. Create Usuario
        UsuarioJpa u = new UsuarioJpa();
        u.setId(p.getId()); // OneToOne shared PK
        u.setIdRol(rolCliente.getId());
        u.setLogin(form.getLogin());
        u.setPassword(form.getPassword());
        u.setActivo(true);
        usuarioJpaRepository.save(u);

        log.info("Registered new client: {}", form.getLogin());

        return "redirect:/?registered=true";
    }
}
