package mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class GeneroForm {
    private Integer id;
    @NotEmpty(message = "El nombre es requerido")
    private String name;
    private String description;
    private Boolean active;
}
