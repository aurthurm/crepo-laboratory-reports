package com.crepo.reports.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LaboratoryInstrument.
 */
@Entity
@Table(name = "laboratory_instrument")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LaboratoryInstrument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "laboratory_id", nullable = false)
    private String laboratoryId;

    @Column(name = "laboratory")
    private String laboratory;

    @NotNull
    @Column(name = "instrument_id", nullable = false)
    private String instrumentId;

    @Column(name = "instrument")
    private String instrument;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "department")
    private String department;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LaboratoryInstrument id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaboratoryId() {
        return this.laboratoryId;
    }

    public LaboratoryInstrument laboratoryId(String laboratoryId) {
        this.setLaboratoryId(laboratoryId);
        return this;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratory() {
        return this.laboratory;
    }

    public LaboratoryInstrument laboratory(String laboratory) {
        this.setLaboratory(laboratory);
        return this;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getInstrumentId() {
        return this.instrumentId;
    }

    public LaboratoryInstrument instrumentId(String instrumentId) {
        this.setInstrumentId(instrumentId);
        return this;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrument() {
        return this.instrument;
    }

    public LaboratoryInstrument instrument(String instrument) {
        this.setInstrument(instrument);
        return this;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public LaboratoryInstrument departmentId(String departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return this.department;
    }

    public LaboratoryInstrument department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LaboratoryInstrument)) {
            return false;
        }
        return getId() != null && getId().equals(((LaboratoryInstrument) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LaboratoryInstrument{" +
            "id=" + getId() +
            ", laboratoryId='" + getLaboratoryId() + "'" +
            ", laboratory='" + getLaboratory() + "'" +
            ", instrumentId='" + getInstrumentId() + "'" +
            ", instrument='" + getInstrument() + "'" +
            ", departmentId='" + getDepartmentId() + "'" +
            ", department='" + getDepartment() + "'" +
            "}";
    }
}
