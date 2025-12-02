package mx.ipn.upiicsa.web.hresources.application.service;

import mx.ipn.upiicsa.web.hresources.application.port.in.SucursalService;
import mx.ipn.upiicsa.web.hresources.application.port.out.SucursalJpaRepository;
import mx.ipn.upiicsa.web.hresources.domain.SucursalJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalJpaRepository sucursalJpaRepository;

    @Override
    public List<SucursalJpa> findAll() {
        return sucursalJpaRepository.findAll();
    }

    @Override
    public SucursalJpa save(SucursalJpa sucursal) {
        return sucursalJpaRepository.save(sucursal);
    }
}
