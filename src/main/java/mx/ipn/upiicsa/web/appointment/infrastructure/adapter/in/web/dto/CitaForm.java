package mx.ipn.upiicsa.web.appointment.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CitaForm {
    private Integer id;
    @NotNull(message = "Seleccione la persona")
    private Integer personId;
    @NotNull(message = "Seleccione el servicio")
    private Integer serviceId;
    @NotNull(message = "Seleccione la lista de precio")
    private Integer priceListId;
    @NotNull(message = "Seleccione la sucursal")
    private Integer branchId;
    @NotNull(message = "Seleccione el empleado")
    private Integer employeeId;
    private LocalDateTime dateTime;
}
