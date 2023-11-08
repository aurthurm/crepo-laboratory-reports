package com.crepo.reports.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LaboratoryTest.
 */
@Entity
@Table(name = "laboratory_test")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LaboratoryTest implements Serializable {

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
    @Column(name = "test_id", nullable = false)
    private String testId;

    @Column(name = "test")
    private String test;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "department")
    private String department;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LaboratoryTest id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaboratoryId() {
        return this.laboratoryId;
    }

    public LaboratoryTest laboratoryId(String laboratoryId) {
        this.setLaboratoryId(laboratoryId);
        return this;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratory() {
        return this.laboratory;
    }

    public LaboratoryTest laboratory(String laboratory) {
        this.setLaboratory(laboratory);
        return this;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getTestId() {
        return this.testId;
    }

    public LaboratoryTest testId(String testId) {
        this.setTestId(testId);
        return this;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTest() {
        return this.test;
    }

    public LaboratoryTest test(String test) {
        this.setTest(test);
        return this;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public LaboratoryTest departmentId(String departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return this.department;
    }

    public LaboratoryTest department(String department) {
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
        if (!(o instanceof LaboratoryTest)) {
            return false;
        }
        return getId() != null && getId().equals(((LaboratoryTest) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LaboratoryTest{" +
            "id=" + getId() +
            ", laboratoryId='" + getLaboratoryId() + "'" +
            ", laboratory='" + getLaboratory() + "'" +
            ", testId='" + getTestId() + "'" +
            ", test='" + getTest() + "'" +
            ", departmentId='" + getDepartmentId() + "'" +
            ", department='" + getDepartment() + "'" +
            "}";
    }
}
