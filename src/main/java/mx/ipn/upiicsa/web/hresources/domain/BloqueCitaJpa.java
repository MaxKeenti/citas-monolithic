package mx.ipn.upiicsa.web.hresources.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import mx.ipn.upiicsa.web.appointment.domain.CitaJpa;

@Data
@Entity
@Table(name = "tce07_bloque_cita")
@IdClass(BloqueCitaId.class)
public class BloqueCitaJpa {
    @Id
    @Column(name = "fk_id_sucursal")
    private Integer branchId;

    @ManyToOne
    @JoinColumn(name = "fk_id_sucursal", insertable = false, updatable = false)
    private SucursalJpa branch;

    @Column(name = "fk_id_cita")
    private Integer appointmentId;

    @ManyToOne
    @JoinColumn(name = "fk_id_cita", insertable = false, updatable = false)
    private CitaJpa appointment;

    @Id
    @Column(name = "fh_inicio")
    private LocalDateTime startDate;

    @Id
    @Column(name = "fh_fin")
    private LocalDateTime endDate;

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

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public CitaJpa getAppointment() {
        return appointment;
    }

    public void setAppointment(CitaJpa appointment) {
        this.appointment = appointment;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
