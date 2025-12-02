package mx.ipn.upiicsa.web.accesscontrol.jpa.repository;

import mx.ipn.upiicsa.web.accesscontrol.jpa.model.UsuarioJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioJpa,Integer>{
    Optional<UsuarioJpa> findByLoginAndPassword(String login, String password);
}
