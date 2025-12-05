package mx.ipn.upiicsa.web.catalog.application.service;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.catalog.application.port.in.EstadoListaPrecioService;
import mx.ipn.upiicsa.web.catalog.application.port.out.EstadoListaPrecioJpaRepository;
import mx.ipn.upiicsa.web.catalog.domain.EstadoListaPrecioJpa;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoListaPrecioServiceImpl implements EstadoListaPrecioService {
    private final EstadoListaPrecioJpaRepository estadoListaPrecioJpaRepository;

    @Override
    public List<EstadoListaPrecioJpa> findAll() {
        return estadoListaPrecioJpaRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public EstadoListaPrecioJpa save(EstadoListaPrecioJpa estadoListaPrecio) {
        return estadoListaPrecioJpaRepository.save(estadoListaPrecio);
    }
}
