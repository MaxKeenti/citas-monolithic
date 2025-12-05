package mx.ipn.upiicsa.web.hresources.application.service;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.hresources.application.port.in.EmpleadoHorarioService;
import mx.ipn.upiicsa.web.hresources.application.port.out.EmpleadoHorarioJpaRepository;
import mx.ipn.upiicsa.web.hresources.domain.EmpleadoHorarioJpa;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoHorarioServiceImpl implements EmpleadoHorarioService {
    private final EmpleadoHorarioJpaRepository empleadoHorarioJpaRepository;

    @Override
    public List<EmpleadoHorarioJpa> findAll() {
        return empleadoHorarioJpaRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public EmpleadoHorarioJpa save(EmpleadoHorarioJpa empleadoHorario) {
        return empleadoHorarioJpaRepository.save(empleadoHorario);
    }
}
