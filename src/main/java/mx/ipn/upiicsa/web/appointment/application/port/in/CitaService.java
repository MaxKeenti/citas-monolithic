package mx.ipn.upiicsa.web.appointment.application.port.in;

import mx.ipn.upiicsa.web.appointment.domain.CitaJpa;
import java.util.List;

public interface CitaService {
    List<CitaJpa> findAll();

    CitaJpa save(CitaJpa cita);
}
