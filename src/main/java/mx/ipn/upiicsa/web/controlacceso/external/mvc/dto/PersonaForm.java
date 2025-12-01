package mx.ipn.upiicsa.web.controlacceso.external.mvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PersonaForm {
    private Integer id;
    @NotNull(message = "Seleccione un género")
    private Integer idGenero;
    @NotBlank(message = "Favor de proporcionar el nombre")
    private String nombre;
    @NotBlank(message = "Favor de proporcionar el primer apellido")
    private String primerApellido;
    private String segundoApellido;

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdGenero() { return idGenero; }
    public void setIdGenero(Integer idGenero) { this.idGenero = idGenero; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }
    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }
}
