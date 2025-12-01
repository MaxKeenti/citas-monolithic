package mx.ipn.upiicsa.web.hresources.jpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sucursal")
public class SucursalJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Integer idSucursal;

    @Column(name = "fk_id_establecimiento")
    private Integer fkIdEstablecimiento;

    @ManyToOne
    @JoinColumn(name = "fk_id_establecimiento", insertable = false, updatable = false)
    private EstablecimientoJpa establecimiento;

    @Column(name = "tx_nombre")
    private String txNombre;

    @Column(name = "gm_ubicacion")
    private String gmUbicacion; // Assuming string for simplicity as per scaffold

    // getters/setters
    public Integer getIdSucursal() { return idSucursal; }
    public void setIdSucursal(Integer idSucursal) { this.idSucursal = idSucursal; }
    public Integer getFkIdEstablecimiento() { return fkIdEstablecimiento; }
    public void setFkIdEstablecimiento(Integer fkIdEstablecimiento) { this.fkIdEstablecimiento = fkIdEstablecimiento; }
    public EstablecimientoJpa getEstablecimiento() { return establecimiento; }
    public void setEstablecimiento(EstablecimientoJpa establecimiento) { this.establecimiento = establecimiento; }
    public String getTxNombre() { return txNombre; }
    public void setTxNombre(String txNombre) { this.txNombre = txNombre; }
    public String getGmUbicacion() { return gmUbicacion; }
    public void setGmUbicacion(String gmUbicacion) { this.gmUbicacion = gmUbicacion; }
}
