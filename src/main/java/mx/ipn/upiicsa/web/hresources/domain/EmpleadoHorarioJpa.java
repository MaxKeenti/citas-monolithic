package mx.ipn.upiicsa.web.hresources.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tce06_empleado_horario")
@IdClass(EmpleadoHorarioId.class)
public class EmpleadoHorarioJpa {
    @Id
    @Column(name = "fk_id_horario")
    private Integer scheduleId;

    @Id
    @Column(name = "fk_id_persona")
    private Integer employeeId;

    @ManyToOne
    @JoinColumn(name = "fk_id_horario", insertable = false, updatable = false)
    private HorarioJpa schedule;

    @ManyToOne
    @JoinColumn(name = "fk_id_persona", insertable = false, updatable = false)
    private EmpleadoJpa employee;

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public HorarioJpa getSchedule() {
        return schedule;
    }

    public void setSchedule(HorarioJpa schedule) {
        this.schedule = schedule;
    }

    public EmpleadoJpa getEmployee() {
        return employee;
    }

    public void setEmployee(EmpleadoJpa employee) {
        this.employee = employee;
    }
}
