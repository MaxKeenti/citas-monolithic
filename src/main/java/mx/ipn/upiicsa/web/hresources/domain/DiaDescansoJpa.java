package mx.ipn.upiicsa.web.hresources.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tce05_dia_descanso")
public class DiaDescansoJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dia_descanso")
    private Integer id;

    @Column(name = "fk_id_empleado")
    private Integer employeeId;

    @ManyToOne
    @JoinColumn(name = "fk_id_empleado", insertable = false, updatable = false)
    private EmpleadoJpa employee;

    @Column(name = "fh_descanso")
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public EmpleadoJpa getEmployee() {
        return employee;
    }

    public void setEmployee(EmpleadoJpa employee) {
        this.employee = employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
