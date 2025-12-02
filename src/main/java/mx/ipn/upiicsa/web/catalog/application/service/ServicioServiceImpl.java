package mx.ipn.upiicsa.web.catalog.application.service;

import mx.ipn.upiicsa.web.catalog.application.port.in.ServicioService;
import mx.ipn.upiicsa.web.catalog.application.port.out.ServicioJpaRepository;
import mx.ipn.upiicsa.web.catalog.domain.ServicioJpa;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@lombok.RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioService {

    private final ServicioJpaRepository servicioJpaRepository;

    @Override
    public List<ServicioJpa> findAll() {
        return servicioJpaRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public ServicioJpa save(ServicioJpa servicio) {
        return servicioJpaRepository.save(servicio);
    }
}
