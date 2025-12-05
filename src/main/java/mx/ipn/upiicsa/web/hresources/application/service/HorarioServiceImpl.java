package mx.ipn.upiicsa.web.hresources.application.service;

import lombok.RequiredArgsConstructor;
import mx.ipn.upiicsa.web.hresources.application.port.in.HorarioService;
import mx.ipn.upiicsa.web.hresources.application.port.out.HorarioJpaRepository;
import mx.ipn.upiicsa.web.hresources.domain.HorarioJpa;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioServiceImpl implements HorarioService {
    private final HorarioJpaRepository horarioJpaRepository;

    @Override
    public List<HorarioJpa> findAll() {
        return horarioJpaRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public HorarioJpa save(HorarioJpa horario) {
        return horarioJpaRepository.save(horario);
    }
}
