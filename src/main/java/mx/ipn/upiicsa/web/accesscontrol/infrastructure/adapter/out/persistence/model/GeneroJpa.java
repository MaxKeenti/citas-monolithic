package mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model;

import jakarta.persistence.*;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cca01_genero")
public class GeneroJpa {
    @Id
    @Column(name = "id_genero")
    private Integer id;
    @Column(name = "tx_nombre")
    private String name;
    @Column(name = "tx_descripcion")
    private String description;
    @Column(name = "st_activo")
    private Boolean active;
    @OneToMany(mappedBy = "genero")
    List<PersonaJpa> personas;
}
