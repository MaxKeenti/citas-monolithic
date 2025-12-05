package mx.ipn.upiicsa.web.hresources.application.port.in;

import mx.ipn.upiicsa.web.hresources.domain.EmpleadoHorarioJpa;
import java.util.List;

public interface EmpleadoHorarioService {
    List<EmpleadoHorarioJpa> findAll();

    EmpleadoHorarioJpa save(EmpleadoHorarioJpa empleadoHorario);
}
