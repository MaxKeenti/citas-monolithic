package mx.ipn.upiicsa.web.catalog.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Data
public class ListaPrecioForm {
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    private String name;

    @NotNull(message = "El estado es requerido")
    private Integer statusId;

    @NotNull(message = "La fecha de inicio es requerida")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    private Boolean active;
}
