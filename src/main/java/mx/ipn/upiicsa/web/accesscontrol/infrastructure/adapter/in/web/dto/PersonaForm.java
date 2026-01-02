package mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PersonaForm {
    private Integer id;
    @NotNull(message = "Seleccione un género")
    private Integer genderId;
    @NotBlank(message = "Favor de proporcionar el nombre")
    private String firstName;
    @NotBlank(message = "Favor de proporcionar el primer apellido")
    private String lastName;
    private String secondLastName;
    @NotNull(message = "Favor de proporcionar la fecha de nacimiento")
    private java.time.LocalDate birthDate;
}
