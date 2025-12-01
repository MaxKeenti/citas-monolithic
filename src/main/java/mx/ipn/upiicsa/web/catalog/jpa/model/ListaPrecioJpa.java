package mx.ipn.upiicsa.web.catalog.jpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lista_precio")
public class ListaPrecioJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lista_precio")
    private Integer idListaPrecio;

    @Column(name = "tx_nombre")
    private String txNombre;

    @Column(name = "st_activo")
    private Boolean stActivo;

    // getters/setters
    public Integer getIdListaPrecio() { return idListaPrecio; }
    public void setIdListaPrecio(Integer idListaPrecio) { this.idListaPrecio = idListaPrecio; }
    public String getTxNombre() { return txNombre; }
    public void setTxNombre(String txNombre) { this.txNombre = txNombre; }
    public Boolean getStActivo() { return stActivo; }
    public void setStActivo(Boolean stActivo) { this.stActivo = stActivo; }
}
