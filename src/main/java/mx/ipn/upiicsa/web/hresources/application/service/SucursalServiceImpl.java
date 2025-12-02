package mx.ipn.upiicsa.web.hresources.application.service;

import mx.ipn.upiicsa.web.hresources.application.port.in.SucursalService;
import mx.ipn.upiicsa.web.hresources.application.port.out.SucursalJpaRepository;
import mx.ipn.upiicsa.web.hresources.domain.SucursalJpa;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@lombok.RequiredArgsConstructor
public class SucursalServiceImpl implements SucursalService {

    private final SucursalJpaRepository sucursalJpaRepository;

    @Override
    public List<SucursalJpa> findAll() {
        return sucursalJpaRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public SucursalJpa save(SucursalJpa sucursal) {
        return sucursalJpaRepository.save(sucursal);
    }
}
