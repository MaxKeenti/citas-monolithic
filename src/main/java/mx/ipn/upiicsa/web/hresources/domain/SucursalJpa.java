package mx.ipn.upiicsa.web.hresources.domain;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "tce02_sucursal")
public class SucursalJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Integer id;

    @Column(name = "fk_id_establecimiento")
    private Integer establishmentId;

    @ManyToOne
    @JoinColumn(name = "fk_id_establecimiento", insertable = false, updatable = false)
    private EstablecimientoJpa establishment;

    @Column(name = "tx_nombre")
    private String name;

    @Column(name = "gm_ubicacion", columnDefinition = "geometry(Point,4326)")
    private Point location;

    // getters/setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(Integer establishmentId) {
        this.establishmentId = establishmentId;
    }

    public EstablecimientoJpa getEstablishment() {
        return establishment;
    }

    public void setEstablishment(EstablecimientoJpa establishment) {
        this.establishment = establishment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
