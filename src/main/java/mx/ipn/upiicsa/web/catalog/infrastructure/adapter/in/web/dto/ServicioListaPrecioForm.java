package mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServicioListaPrecioForm {
    private Integer id;

    @NotNull(message = "El servicio es requerido")
    private Integer serviceId;

    @NotNull(message = "La lista de precios es requerida")
    private Integer priceListId;

    @NotNull(message = "El precio es requerido")
    private Integer price;
}
