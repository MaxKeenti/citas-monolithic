package mx.ipn.upiicsa.web.appointment.application.port.out;

import mx.ipn.upiicsa.web.appointment.domain.CitaJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaJpaRepository extends JpaRepository<CitaJpa,Integer> {
}
