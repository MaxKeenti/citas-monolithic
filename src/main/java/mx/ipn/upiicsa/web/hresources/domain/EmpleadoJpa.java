package mx.ipn.upiicsa.web.hresources.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tce03_empleado")
public class EmpleadoJpa {
    @Id
    @Column(name = "id_empleado")
    private Integer id; // Same as Persona ID

    @Column(name = "fk_id_sucursal")
    private Integer branchId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_persona", insertable = false, updatable = false)
    private mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model.PersonaJpa person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_sucursal", insertable = false, updatable = false)
    private SucursalJpa branch;

    // getters/setters
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

    public mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model.PersonaJpa getPerson() {
        return person;
    }

    public void setPerson(
            mx.ipn.upiicsa.web.accesscontrol.infrastructure.adapter.out.persistence.model.PersonaJpa person) {
        this.person = person;
    }

    public SucursalJpa getBranch() {
        return branch;
    }

    public void setBranch(SucursalJpa branch) {
        this.branch = branch;
    }
}
