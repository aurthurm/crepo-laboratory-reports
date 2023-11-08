package com.crepo.reports.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A StaffCompiment.
 */
@Entity
@Table(name = "staff_compiment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StaffCompiment implements Serializable {

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

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "department")
    private String department;

    @Column(name = "scientist_available")
    private Long scientistAvailable;

    @Column(name = "scientist_required")
    private Long scientistRequired;

    @Column(name = "microscopits_available")
    private Long microscopitsAvailable;

    @Column(name = "microscopits_required")
    private Long microscopitsRequired;

    @Column(name = "lab_techs_available")
    private Long labTechsAvailable;

    @Column(name = "lab_techs_required")
    private Long labTechsRequired;

    @Column(name = "general_hands_available")
    private Long generalHandsAvailable;

    @Column(name = "general_hands_required")
    private Long generalHandsRequired;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public StaffCompiment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaboratoryId() {
        return this.laboratoryId;
    }

    public StaffCompiment laboratoryId(String laboratoryId) {
        this.setLaboratoryId(laboratoryId);
        return this;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratory() {
        return this.laboratory;
    }

    public StaffCompiment laboratory(String laboratory) {
        this.setLaboratory(laboratory);
        return this;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public StaffCompiment departmentId(String departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return this.department;
    }

    public StaffCompiment department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getScientistAvailable() {
        return this.scientistAvailable;
    }

    public StaffCompiment scientistAvailable(Long scientistAvailable) {
        this.setScientistAvailable(scientistAvailable);
        return this;
    }

    public void setScientistAvailable(Long scientistAvailable) {
        this.scientistAvailable = scientistAvailable;
    }

    public Long getScientistRequired() {
        return this.scientistRequired;
    }

    public StaffCompiment scientistRequired(Long scientistRequired) {
        this.setScientistRequired(scientistRequired);
        return this;
    }

    public void setScientistRequired(Long scientistRequired) {
        this.scientistRequired = scientistRequired;
    }

    public Long getMicroscopitsAvailable() {
        return this.microscopitsAvailable;
    }

    public StaffCompiment microscopitsAvailable(Long microscopitsAvailable) {
        this.setMicroscopitsAvailable(microscopitsAvailable);
        return this;
    }

    public void setMicroscopitsAvailable(Long microscopitsAvailable) {
        this.microscopitsAvailable = microscopitsAvailable;
    }

    public Long getMicroscopitsRequired() {
        return this.microscopitsRequired;
    }

    public StaffCompiment microscopitsRequired(Long microscopitsRequired) {
        this.setMicroscopitsRequired(microscopitsRequired);
        return this;
    }

    public void setMicroscopitsRequired(Long microscopitsRequired) {
        this.microscopitsRequired = microscopitsRequired;
    }

    public Long getLabTechsAvailable() {
        return this.labTechsAvailable;
    }

    public StaffCompiment labTechsAvailable(Long labTechsAvailable) {
        this.setLabTechsAvailable(labTechsAvailable);
        return this;
    }

    public void setLabTechsAvailable(Long labTechsAvailable) {
        this.labTechsAvailable = labTechsAvailable;
    }

    public Long getLabTechsRequired() {
        return this.labTechsRequired;
    }

    public StaffCompiment labTechsRequired(Long labTechsRequired) {
        this.setLabTechsRequired(labTechsRequired);
        return this;
    }

    public void setLabTechsRequired(Long labTechsRequired) {
        this.labTechsRequired = labTechsRequired;
    }

    public Long getGeneralHandsAvailable() {
        return this.generalHandsAvailable;
    }

    public StaffCompiment generalHandsAvailable(Long generalHandsAvailable) {
        this.setGeneralHandsAvailable(generalHandsAvailable);
        return this;
    }

    public void setGeneralHandsAvailable(Long generalHandsAvailable) {
        this.generalHandsAvailable = generalHandsAvailable;
    }

    public Long getGeneralHandsRequired() {
        return this.generalHandsRequired;
    }

    public StaffCompiment generalHandsRequired(Long generalHandsRequired) {
        this.setGeneralHandsRequired(generalHandsRequired);
        return this;
    }

    public void setGeneralHandsRequired(Long generalHandsRequired) {
        this.generalHandsRequired = generalHandsRequired;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StaffCompiment)) {
            return false;
        }
        return getId() != null && getId().equals(((StaffCompiment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StaffCompiment{" +
            "id=" + getId() +
            ", laboratoryId='" + getLaboratoryId() + "'" +
            ", laboratory='" + getLaboratory() + "'" +
            ", departmentId='" + getDepartmentId() + "'" +
            ", department='" + getDepartment() + "'" +
            ", scientistAvailable=" + getScientistAvailable() +
            ", scientistRequired=" + getScientistRequired() +
            ", microscopitsAvailable=" + getMicroscopitsAvailable() +
            ", microscopitsRequired=" + getMicroscopitsRequired() +
            ", labTechsAvailable=" + getLabTechsAvailable() +
            ", labTechsRequired=" + getLabTechsRequired() +
            ", generalHandsAvailable=" + getGeneralHandsAvailable() +
            ", generalHandsRequired=" + getGeneralHandsRequired() +
            "}";
    }
}
