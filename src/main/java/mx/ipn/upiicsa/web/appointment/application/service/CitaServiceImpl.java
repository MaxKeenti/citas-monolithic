package mx.ipn.upiicsa.web.appointment.application.service;

import mx.ipn.upiicsa.web.appointment.application.port.in.CitaService;
import mx.ipn.upiicsa.web.appointment.application.port.out.CitaJpaRepository;
import mx.ipn.upiicsa.web.appointment.domain.CitaJpa;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@lombok.RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaJpaRepository citaJpaRepository;

    @Override
    public List<CitaJpa> findAll() {
        return citaJpaRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public CitaJpa save(CitaJpa cita) {
        return citaJpaRepository.save(cita);
    }
}
