package mx.ipn.upiicsa.web.catalog.jpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "servicio_lista_precio")
public class ServicioListaPrecioJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio_lista_precio")
    private Integer id;

    @Column(name = "fk_id_servicio")
    private Integer fkIdServicio;

    @Column(name = "fk_id_lista_precio")
    private Integer fkIdListaPrecio;

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getFkIdServicio() { return fkIdServicio; }
    public void setFkIdServicio(Integer fkIdServicio) { this.fkIdServicio = fkIdServicio; }
    public Integer getFkIdListaPrecio() { return fkIdListaPrecio; }
    public void setFkIdListaPrecio(Integer fkIdListaPrecio) { this.fkIdListaPrecio = fkIdListaPrecio; }
}
