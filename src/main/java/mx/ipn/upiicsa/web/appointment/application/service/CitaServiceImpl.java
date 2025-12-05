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
    public CitaJpa save(CitaJpa cita) {
        if (cita == null) {
            throw new IllegalArgumentException("cita cannot be null");
        }
        return citaJpaRepository.save(cita);
    }

    @Override
    public java.util.Optional<CitaJpa> findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        return citaJpaRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        citaJpaRepository.deleteById(id);
    }
}
