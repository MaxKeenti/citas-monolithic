package mx.ipn.upiicsa.web.catalog.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tci02_servicio_lista_precio")
public class ServicioListaPrecioJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio_lista_precio")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_servicio")
    private ServicioJpa service;

    @ManyToOne
    @JoinColumn(name = "fk_id_lista_precio")
    private ListaPrecioJpa priceList;

    @Column(name = "nu_precio")
    private Integer price;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setService(ServicioJpa service) {
        this.service = service;
    }

    public ServicioJpa getService() {
        return service;
    }

    public void setPriceList(ListaPrecioJpa priceList) {
        this.priceList = priceList;
    }

    public ListaPrecioJpa getPriceList() {
        return priceList;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
