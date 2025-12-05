package mx.ipn.upiicsa.web.hresources.application.service;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.hresources.application.port.in.DiaDescansoService;
import mx.ipn.upiicsa.web.hresources.application.port.out.DiaDescansoJpaRepository;
import mx.ipn.upiicsa.web.hresources.domain.DiaDescansoJpa;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaDescansoServiceImpl implements DiaDescansoService {
    private final DiaDescansoJpaRepository diaDescansoJpaRepository;

    @Override
    public List<DiaDescansoJpa> findAll() {
        return diaDescansoJpaRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public DiaDescansoJpa save(DiaDescansoJpa diaDescanso) {
        return diaDescansoJpaRepository.save(diaDescanso);
    }
}
