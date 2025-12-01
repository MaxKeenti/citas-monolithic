package mx.ipn.upiicsa.web.hresources.mvc.dto;

import jakarta.validation.constraints.NotNull;

public class EmpleadoForm {
    private Integer idEmpleado; // Will be persona id
    @NotNull(message = "Seleccione la persona")
    private Integer idPersona;
    @NotNull(message = "Seleccione la sucursal")
    private Integer idSucursal;

    // getters/setters
    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }
    public Integer getIdPersona() { return idPersona; }
    public void setIdPersona(Integer idPersona) { this.idPersona = idPersona; }
    public Integer getIdSucursal() { return idSucursal; }
    public void setIdSucursal(Integer idSucursal) { this.idSucursal = idSucursal; }
}
