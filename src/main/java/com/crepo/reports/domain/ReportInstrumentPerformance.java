package com.crepo.reports.domain;

import com.crepo.reports.domain.enumeration.InstrumentFunctionality;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ReportInstrumentPerformance.
 */
@Entity
@Table(name = "report_instrument_performance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReportInstrumentPerformance implements Serializable {

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

    @NotNull
    @Column(name = "instrument_id", nullable = false)
    private String instrumentId;

    @Column(name = "instrument")
    private String instrument;

    @Column(name = "status")
    private String status;

    @Column(name = "uptime")
    private String uptime;

    @Column(name = "downtime")
    private String downtime;

    @Column(name = "service_status")
    private String serviceStatus;

    @Column(name = "caliberation_status")
    private String caliberationStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "functionality")
    private InstrumentFunctionality functionality;

    @Column(name = "comment")
    private String comment;

    @NotNull
    @Column(name = "reporting_period_id", nullable = false)
    private String reportingPeriodId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReportInstrumentPerformance id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaboratoryId() {
        return this.laboratoryId;
    }

    public ReportInstrumentPerformance laboratoryId(String laboratoryId) {
        this.setLaboratoryId(laboratoryId);
        return this;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratory() {
        return this.laboratory;
    }

    public ReportInstrumentPerformance laboratory(String laboratory) {
        this.setLaboratory(laboratory);
        return this;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public ReportInstrumentPerformance departmentId(String departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return this.department;
    }

    public ReportInstrumentPerformance department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getInstrumentId() {
        return this.instrumentId;
    }

    public ReportInstrumentPerformance instrumentId(String instrumentId) {
        this.setInstrumentId(instrumentId);
        return this;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrument() {
        return this.instrument;
    }

    public ReportInstrumentPerformance instrument(String instrument) {
        this.setInstrument(instrument);
        return this;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getStatus() {
        return this.status;
    }

    public ReportInstrumentPerformance status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUptime() {
        return this.uptime;
    }

    public ReportInstrumentPerformance uptime(String uptime) {
        this.setUptime(uptime);
        return this;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getDowntime() {
        return this.downtime;
    }

    public ReportInstrumentPerformance downtime(String downtime) {
        this.setDowntime(downtime);
        return this;
    }

    public void setDowntime(String downtime) {
        this.downtime = downtime;
    }

    public String getServiceStatus() {
        return this.serviceStatus;
    }

    public ReportInstrumentPerformance serviceStatus(String serviceStatus) {
        this.setServiceStatus(serviceStatus);
        return this;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getCaliberationStatus() {
        return this.caliberationStatus;
    }

    public ReportInstrumentPerformance caliberationStatus(String caliberationStatus) {
        this.setCaliberationStatus(caliberationStatus);
        return this;
    }

    public void setCaliberationStatus(String caliberationStatus) {
        this.caliberationStatus = caliberationStatus;
    }

    public InstrumentFunctionality getFunctionality() {
        return this.functionality;
    }

    public ReportInstrumentPerformance functionality(InstrumentFunctionality functionality) {
        this.setFunctionality(functionality);
        return this;
    }

    public void setFunctionality(InstrumentFunctionality functionality) {
        this.functionality = functionality;
    }

    public String getComment() {
        return this.comment;
    }

    public ReportInstrumentPerformance comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReportingPeriodId() {
        return this.reportingPeriodId;
    }

    public ReportInstrumentPerformance reportingPeriodId(String reportingPeriodId) {
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
        if (!(o instanceof ReportInstrumentPerformance)) {
            return false;
        }
        return getId() != null && getId().equals(((ReportInstrumentPerformance) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportInstrumentPerformance{" +
            "id=" + getId() +
            ", laboratoryId='" + getLaboratoryId() + "'" +
            ", laboratory='" + getLaboratory() + "'" +
            ", departmentId='" + getDepartmentId() + "'" +
            ", department='" + getDepartment() + "'" +
            ", instrumentId='" + getInstrumentId() + "'" +
            ", instrument='" + getInstrument() + "'" +
            ", status='" + getStatus() + "'" +
            ", uptime='" + getUptime() + "'" +
            ", downtime='" + getDowntime() + "'" +
            ", serviceStatus='" + getServiceStatus() + "'" +
            ", caliberationStatus='" + getCaliberationStatus() + "'" +
            ", functionality='" + getFunctionality() + "'" +
            ", comment='" + getComment() + "'" +
            ", reportingPeriodId='" + getReportingPeriodId() + "'" +
            "}";
    }
}
