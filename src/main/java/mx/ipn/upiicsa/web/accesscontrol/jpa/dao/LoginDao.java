package mx.ipn.upiicsa.web.accesscontrol.jpa.dao;


import mx.ipn.upiicsa.web.accesscontrol.jpa.repository.UsuarioJpaRepository;
import mx.ipn.upiicsa.web.accesscontrol.repository.LoginRepository;
import mx.ipn.upiicsa.web.accesscontrol.service.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginDao implements LoginRepository {
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;
    
    public Optional<Persona> findByLoginAndPassword(String login, String password) {
        var resultado = usuarioJpaRepository.findByLoginAndPassword(login, password);
        if(resultado.isPresent()) {
            var usuarioJpa = resultado.get();
            var persona = usuarioJpa.getPersona().toEntity();
            persona.setUsuario(usuarioJpa.toEntity());
            return Optional.of(persona);
        } else {
            return Optional.empty();
        }
    }
}
