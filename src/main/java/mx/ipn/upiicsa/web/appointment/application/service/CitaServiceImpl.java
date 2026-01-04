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
    private final mx.ipn.upiicsa.web.hresources.application.port.in.BloqueCitaService bloqueCitaService;
    private final mx.ipn.upiicsa.web.catalog.application.port.out.ServicioJpaRepository servicioJpaRepository;

    @Override
    public List<CitaJpa> findAll() {
        return citaJpaRepository.findAll();
    }

    @Override
    public CitaJpa save(CitaJpa cita) {
        if (cita == null) {
            throw new IllegalArgumentException("cita cannot be null");
        }

        CitaJpa savedCita = citaJpaRepository.save(cita);

        // Logic to create BloqueCita entries
        Integer duration = savedCita.getCustomDuration();
        if (duration == null) {
            // Fetch service to get default duration
            Integer serviceId = savedCita.getFkIdServicio();
            if (serviceId != null) {
                var servicio = servicioJpaRepository.findById(serviceId).orElse(null);
                if (servicio != null) {
                    duration = servicio.getNuDuracion();
                } else {
                    duration = 30; // Fallback
                }
            } else {
                duration = 30; // Fallback if no service ID
            }
        }

        int blocks = (int) Math.ceil((double) duration / 30);
        java.time.LocalDateTime startTime = savedCita.getFechaHora();

        for (int i = 0; i < blocks; i++) {
            java.time.LocalDateTime blockStart = startTime.plusMinutes(i * 30);
            java.time.LocalDateTime blockEnd = blockStart.plusMinutes(30);

            mx.ipn.upiicsa.web.hresources.domain.BloqueCitaJpa bloque = new mx.ipn.upiicsa.web.hresources.domain.BloqueCitaJpa();
            bloque.setIdSucursal(savedCita.getFkIdSucursal());
            bloque.setIdCita(savedCita.getIdCita());
            bloque.setFechaInicio(blockStart);
            bloque.setFechaFin(blockEnd);

            bloqueCitaService.save(bloque);
        }

        return savedCita;
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

    @Override
    public List<CitaJpa> findByPersonaId(Integer idPersona) {
        if (idPersona == null) {
            throw new IllegalArgumentException("idPersona cannot be null");
        }
        return citaJpaRepository.findByFkIdPersona(idPersona);
    }

    @Override
    public List<CitaJpa> findByDateRange(java.time.LocalDateTime start, java.time.LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end dates cannot be null");
        }
        return citaJpaRepository.findByFechaHoraBetween(start, end);
    }

}
