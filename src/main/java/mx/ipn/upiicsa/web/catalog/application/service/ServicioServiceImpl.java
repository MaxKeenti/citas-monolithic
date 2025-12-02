package mx.ipn.upiicsa.web.catalog.application.service;

import mx.ipn.upiicsa.web.catalog.application.port.in.ServicioService;
import mx.ipn.upiicsa.web.catalog.application.port.out.ServicioJpaRepository;
import mx.ipn.upiicsa.web.catalog.domain.ServicioJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioJpaRepository servicioJpaRepository;

    @Override
    public List<ServicioJpa> findAll() {
        return servicioJpaRepository.findAll();
    }

    @Override
    public ServicioJpa save(ServicioJpa servicio) {
        return servicioJpaRepository.save(servicio);
    }
}
