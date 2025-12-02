package mx.ipn.upiicsa.web.accesscontrol.repository;

import mx.ipn.upiicsa.web.accesscontrol.service.model.Persona;

import java.util.Optional;

public interface LoginRepository {
    Optional<Persona> findByLoginAndPassword(String login, String password);
}
