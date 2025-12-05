package mx.ipn.upiicsa.web.appointment.mvc.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CitaForm {
    private Integer id;
    @NotNull(message = "Seleccione la persona")
    private Integer idPersona;
    @NotNull(message = "Seleccione el servicio")
    private Integer idServicio;
    @NotNull(message = "Seleccione la lista de precio")
    private Integer idListaPrecio;
    @NotNull(message = "Seleccione la sucursal")
    private Integer idSucursal;
    @NotNull(message = "Seleccione el empleado")
    private Integer idEmpleado;
    private LocalDateTime fechaHora;

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdPersona() { return idPersona; }
    public void setIdPersona(Integer idPersona) { this.idPersona = idPersona; }
    public Integer getIdServicio() { return idServicio; }
    public void setIdServicio(Integer idServicio) { this.idServicio = idServicio; }
    public Integer getIdListaPrecio() { return idListaPrecio; }
    public void setIdListaPrecio(Integer idListaPrecio) { this.idListaPrecio = idListaPrecio; }
    public Integer getIdSucursal() { return idSucursal; }
    public void setIdSucursal(Integer idSucursal) { this.idSucursal = idSucursal; }
    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
