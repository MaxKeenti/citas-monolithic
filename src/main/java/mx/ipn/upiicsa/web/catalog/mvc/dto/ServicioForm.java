package mx.ipn.upiicsa.web.catalog.mvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ServicioForm {
    private Integer id;
    @NotBlank(message = "Favor de proporcionar el nombre del servicio")
    private String nombre;
    private String descripcion;
    @NotNull(message = "Favor de proporcionar la duración en minutos")
    private Integer duracion;
    private Boolean activo = true;

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getDuracion() { return duracion; }
    public void setDuracion(Integer duracion) { this.duracion = duracion; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
