package mx.ipn.upiicsa.web.controlacceso.external.mvc.controller;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.GeneroJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.PersonaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.GeneroJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.PersonaJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.mvc.dto.PersonaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private PersonaJpaRepository personaJpaRepository;
    @Autowired
    private GeneroJpaRepository generoJpaRepository;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("personaForm", new PersonaForm());
        List<GeneroJpa> generos = generoJpaRepository.findAll();
        model.addAttribute("generos", generos);
        return "personas/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("personaForm") PersonaForm form, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("generos", generoJpaRepository.findAll());
            return "personas/create";
        }
        PersonaJpa p = new PersonaJpa();
        p.setIdGenero(form.getIdGenero());
        p.setNombre(form.getNombre());
        p.setPrimerApellido(form.getPrimerApellido());
        p.setSegundoApellido(form.getSegundoApellido());
        p.setFechaNacimiento(form.getFechaNacimiento());
        personaJpaRepository.save(p);
        return "redirect:/personas/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("personas", personaJpaRepository.findAll());
        return "personas/list";
    }
}
