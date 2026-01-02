package mx.ipn.upiicsa.web.hresources.domain;

import lombok.Data;
import java.io.Serializable;

@Data
public class EmpleadoHorarioId implements Serializable {
    private Integer scheduleId;
    private Integer employeeId;

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
}
