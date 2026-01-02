package mx.ipn.upiicsa.web.hresources.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "tce08_horario")
public class HorarioJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Integer id;

    @Column(name = "fk_id_sucursal")
    private Integer branchId;

    @ManyToOne
    @JoinColumn(name = "fk_id_sucursal", insertable = false, updatable = false)
    private SucursalJpa branch;

    @Column(name = "fk_id_dia")
    private Integer dayId;

    @ManyToOne
    @JoinColumn(name = "fk_id_dia", insertable = false, updatable = false)
    private DiaLaboralJpa workingDay;

    @Column(name = "tm_inicio")
    private LocalTime startTime;

    @Column(name = "tm_fin")
    private LocalTime endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public SucursalJpa getBranch() {
        return branch;
    }

    public void setBranch(SucursalJpa branch) {
        this.branch = branch;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public DiaLaboralJpa getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(DiaLaboralJpa workingDay) {
        this.workingDay = workingDay;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
