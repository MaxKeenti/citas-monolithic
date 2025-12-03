package mx.ipn.upiicsa.web.hresources.mvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SucursalForm {
    private Integer id;
    @NotNull(message = "Seleccione un establecimiento")
    private Integer idEstablecimiento;
    @NotBlank(message = "Favor de proporcionar el nombre de la sucursal")
    private String nombre;
    private String ubicacion; // placeholder for geometry

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdEstablecimiento() { return idEstablecimiento; }
    public void setIdEstablecimiento(Integer idEstablecimiento) { this.idEstablecimiento = idEstablecimiento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}
