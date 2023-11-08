package com.crepo.reports.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LaboratoryStock.
 */
@Entity
@Table(name = "laboratory_stock")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LaboratoryStock implements Serializable {

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
    @Column(name = "stock_item_id", nullable = false)
    private String stockItemId;

    @Column(name = "stock_item")
    private String stockItem;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "department")
    private String department;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LaboratoryStock id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaboratoryId() {
        return this.laboratoryId;
    }

    public LaboratoryStock laboratoryId(String laboratoryId) {
        this.setLaboratoryId(laboratoryId);
        return this;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratory() {
        return this.laboratory;
    }

    public LaboratoryStock laboratory(String laboratory) {
        this.setLaboratory(laboratory);
        return this;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getStockItemId() {
        return this.stockItemId;
    }

    public LaboratoryStock stockItemId(String stockItemId) {
        this.setStockItemId(stockItemId);
        return this;
    }

    public void setStockItemId(String stockItemId) {
        this.stockItemId = stockItemId;
    }

    public String getStockItem() {
        return this.stockItem;
    }

    public LaboratoryStock stockItem(String stockItem) {
        this.setStockItem(stockItem);
        return this;
    }

    public void setStockItem(String stockItem) {
        this.stockItem = stockItem;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public LaboratoryStock departmentId(String departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return this.department;
    }

    public LaboratoryStock department(String department) {
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
        if (!(o instanceof LaboratoryStock)) {
            return false;
        }
        return getId() != null && getId().equals(((LaboratoryStock) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LaboratoryStock{" +
            "id=" + getId() +
            ", laboratoryId='" + getLaboratoryId() + "'" +
            ", laboratory='" + getLaboratory() + "'" +
            ", stockItemId='" + getStockItemId() + "'" +
            ", stockItem='" + getStockItem() + "'" +
            ", departmentId='" + getDepartmentId() + "'" +
            ", department='" + getDepartment() + "'" +
            "}";
    }
}
