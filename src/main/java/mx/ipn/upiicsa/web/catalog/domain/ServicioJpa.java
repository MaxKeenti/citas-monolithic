package mx.ipn.upiicsa.web.catalog.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "cci01_servicio")
public class ServicioJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer id;

    @Column(name = "tx_nombre")
    private String name;

    @Column(name = "tx_descripcion")
    private String description;

    @Column(name = "nu_duracion")
    private Integer duration;

    @Column(name = "st_activo")
    private Boolean active;

    // getters/setters
    // Aliases not needed if we refactor everything, but keeping for safety during
    // transition or removing if cleanly refactoring.
    // Let's rely on Lombok @Data or standard Getters/Setters. The file didn't have
    // @Data but manual getters.
    // I will replace manual getters with Lombok @Data or just new manual getters.
    // The previous file content had manual getters/setters. I'll replace them with
    // manual English ones for now to match style,
    // or better, just use the new names.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
