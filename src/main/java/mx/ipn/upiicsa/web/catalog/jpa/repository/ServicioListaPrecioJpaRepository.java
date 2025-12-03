package mx.ipn.upiicsa.web.catalog.jpa.repository;

import mx.ipn.upiicsa.web.catalog.jpa.model.ServicioListaPrecioJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServicioListaPrecioJpaRepository extends JpaRepository<ServicioListaPrecioJpa,Integer> {
    List<ServicioListaPrecioJpa> findByFkIdServicio(Integer fkIdServicio);
}
