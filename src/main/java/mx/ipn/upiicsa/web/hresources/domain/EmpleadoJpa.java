package mx.ipn.upiicsa.web.hresources.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tce03_empleado")
public class EmpleadoJpa {
    @Id
    @Column(name = "id_empleado")
    private Integer idEmpleado; // Same as Persona ID

    @Column(name = "fk_id_sucursal")
    private Integer fkIdSucursal;

    // getters/setters
    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }
    public Integer getFkIdSucursal() { return fkIdSucursal; }
    public void setFkIdSucursal(Integer fkIdSucursal) { this.fkIdSucursal = fkIdSucursal; }
}
