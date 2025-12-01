package mx.ipn.upiicsa.web.appointment.jpa.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cita")
public class CitaJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer idCita;

    @Column(name = "fk_id_persona")
    private Integer fkIdPersona;

    @Column(name = "fk_id_servicio")
    private Integer fkIdServicio;

    @Column(name = "fk_id_lista_precio")
    private Integer fkIdListaPrecio;

    @Column(name = "fk_id_sucursal")
    private Integer fkIdSucursal;

    @Column(name = "fk_id_empleado")
    private Integer fkIdEmpleado;

    @Column(name = "fh_cita")
    private LocalDateTime fechaHora;

    // getters/setters
    public Integer getIdCita() { return idCita; }
    public void setIdCita(Integer idCita) { this.idCita = idCita; }
    public Integer getFkIdPersona() { return fkIdPersona; }
    public void setFkIdPersona(Integer fkIdPersona) { this.fkIdPersona = fkIdPersona; }
    public Integer getFkIdServicio() { return fkIdServicio; }
    public void setFkIdServicio(Integer fkIdServicio) { this.fkIdServicio = fkIdServicio; }
    public Integer getFkIdListaPrecio() { return fkIdListaPrecio; }
    public void setFkIdListaPrecio(Integer fkIdListaPrecio) { this.fkIdListaPrecio = fkIdListaPrecio; }
    public Integer getFkIdSucursal() { return fkIdSucursal; }
    public void setFkIdSucursal(Integer fkIdSucursal) { this.fkIdSucursal = fkIdSucursal; }
    public Integer getFkIdEmpleado() { return fkIdEmpleado; }
    public void setFkIdEmpleado(Integer fkIdEmpleado) { this.fkIdEmpleado = fkIdEmpleado; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
