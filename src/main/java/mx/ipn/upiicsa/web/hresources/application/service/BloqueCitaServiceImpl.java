package mx.ipn.upiicsa.web.hresources.application.service;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.hresources.application.port.in.BloqueCitaService;
import mx.ipn.upiicsa.web.hresources.application.port.out.BloqueCitaJpaRepository;
import mx.ipn.upiicsa.web.hresources.domain.BloqueCitaJpa;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BloqueCitaServiceImpl implements BloqueCitaService {
    private final BloqueCitaJpaRepository bloqueCitaJpaRepository;

    @Override
    public List<BloqueCitaJpa> findAll() {
        return bloqueCitaJpaRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public BloqueCitaJpa save(BloqueCitaJpa bloqueCita) {
        return bloqueCitaJpaRepository.save(bloqueCita);
    }
}
