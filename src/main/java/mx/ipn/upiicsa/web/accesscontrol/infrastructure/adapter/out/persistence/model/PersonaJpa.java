package mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model;

import jakarta.persistence.*;
import lombok.*;
import mx.ipn.upiicsa.web.accesscontrol.domain.Persona;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tca01_persona")
public class PersonaJpa {
    @Id
    @SequenceGenerator(name = "tca01_persona_id_persona_seq", sequenceName = "tca01_persona_id_persona_seq", allocationSize = 1)
    @GeneratedValue(generator = "tca01_persona_id_persona_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_persona")
    private Integer id;
    @Column(name = "fk_id_genero")
    private Integer genderId;
    @Column(name = "tx_nombre")
    private String firstName;
    @Column(name = "tx_primer_apellido")
    private String lastName;
    @Column(name = "tx_segundo_apellido")
    private String secondLastName;
    @Column(name = "fh_nacimiento")
    private java.time.LocalDate birthDate;
    @ManyToOne
    @JoinColumn(name = "fk_id_genero", referencedColumnName = "id_genero", insertable = false, updatable = false)
    private GeneroJpa genero;
    @OneToOne(mappedBy = "person")
    private UsuarioJpa user;

    public Persona toEntity() {
        return Persona.builder()
                .id(this.id)
                .genderId(this.genderId)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .secondLastName(this.secondLastName)
                .build();
    }
}
