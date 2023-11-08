package com.crepo.reports.domain;

import com.crepo.reports.domain.enumeration.StockAvailability;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ReportStockOut.
 */
@Entity
@Table(name = "report_stock_out")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReportStockOut implements Serializable {

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

    @Enumerated(EnumType.STRING)
    @Column(name = "available")
    private StockAvailability available;

    @Column(name = "comment")
    private String comment;

    @NotNull
    @Column(name = "reporting_period_id", nullable = false)
    private String reportingPeriodId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReportStockOut id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaboratoryId() {
        return this.laboratoryId;
    }

    public ReportStockOut laboratoryId(String laboratoryId) {
        this.setLaboratoryId(laboratoryId);
        return this;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratory() {
        return this.laboratory;
    }

    public ReportStockOut laboratory(String laboratory) {
        this.setLaboratory(laboratory);
        return this;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getStockItemId() {
        return this.stockItemId;
    }

    public ReportStockOut stockItemId(String stockItemId) {
        this.setStockItemId(stockItemId);
        return this;
    }

    public void setStockItemId(String stockItemId) {
        this.stockItemId = stockItemId;
    }

    public String getStockItem() {
        return this.stockItem;
    }

    public ReportStockOut stockItem(String stockItem) {
        this.setStockItem(stockItem);
        return this;
    }

    public void setStockItem(String stockItem) {
        this.stockItem = stockItem;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public ReportStockOut departmentId(String departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return this.department;
    }

    public ReportStockOut department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public StockAvailability getAvailable() {
        return this.available;
    }

    public ReportStockOut available(StockAvailability available) {
        this.setAvailable(available);
        return this;
    }

    public void setAvailable(StockAvailability available) {
        this.available = available;
    }

    public String getComment() {
        return this.comment;
    }

    public ReportStockOut comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReportingPeriodId() {
        return this.reportingPeriodId;
    }

    public ReportStockOut reportingPeriodId(String reportingPeriodId) {
        this.setReportingPeriodId(reportingPeriodId);
        return this;
    }

    public void setReportingPeriodId(String reportingPeriodId) {
        this.reportingPeriodId = reportingPeriodId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportStockOut)) {
            return false;
        }
        return getId() != null && getId().equals(((ReportStockOut) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportStockOut{" +
            "id=" + getId() +
            ", laboratoryId='" + getLaboratoryId() + "'" +
            ", laboratory='" + getLaboratory() + "'" +
            ", stockItemId='" + getStockItemId() + "'" +
            ", stockItem='" + getStockItem() + "'" +
            ", departmentId='" + getDepartmentId() + "'" +
            ", department='" + getDepartment() + "'" +
            ", available='" + getAvailable() + "'" +
            ", comment='" + getComment() + "'" +
            ", reportingPeriodId='" + getReportingPeriodId() + "'" +
            "}";
    }
}
