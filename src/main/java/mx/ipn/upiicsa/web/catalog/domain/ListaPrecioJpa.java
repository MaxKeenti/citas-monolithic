package mx.ipn.upiicsa.web.catalog.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime; // Correct import for timestamp
import org.springframework.format.annotation.DateTimeFormat; // For binding

@Data
@Entity
@Table(name = "tci03_lista_precio")
public class ListaPrecioJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lista_precio")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_estado")
    private EstadoListaPrecioJpa status;

    @Column(name = "tx_nombre")
    private String name;

    @Column(name = "fh_inicio")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @Column(name = "fh_fin")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    @Column(name = "st_activo")
    private Boolean active;
}
