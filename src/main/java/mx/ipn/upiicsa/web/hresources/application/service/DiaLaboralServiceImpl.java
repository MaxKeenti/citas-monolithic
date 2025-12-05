package mx.ipn.upiicsa.web.hresources.application.service;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.hresources.application.port.in.DiaLaboralService;
import mx.ipn.upiicsa.web.hresources.application.port.out.DiaLaboralJpaRepository;
import mx.ipn.upiicsa.web.hresources.domain.DiaLaboralJpa;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaLaboralServiceImpl implements DiaLaboralService {
    private final DiaLaboralJpaRepository diaLaboralJpaRepository;

    @Override
    public List<DiaLaboralJpa> findAll() {
        return diaLaboralJpaRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public DiaLaboralJpa save(DiaLaboralJpa diaLaboral) {
        return diaLaboralJpaRepository.save(diaLaboral);
    }
}
