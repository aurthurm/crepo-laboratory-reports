package com.crepo.reports.domain;

import com.crepo.reports.domain.enumeration.CriticalResult;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ReportTestPeformance.
 */
@Entity
@Table(name = "report_test_peformance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReportTestPeformance implements Serializable {

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
    @Column(name = "sample_type_id", nullable = false)
    private String sampleTypeId;

    @Column(name = "sample_type")
    private String sampleType;

    @NotNull
    @Column(name = "test_id", nullable = false)
    private String testId;

    @Column(name = "test")
    private String test;

    @Column(name = "turn_around_time")
    private String turnAroundTime;

    @Column(name = "number_tested")
    private String numberTested;

    @Column(name = "number_dispatched")
    private String numberDispatched;

    @Column(name = "number_rejected")
    private String numberRejected;

    @NotNull
    @Column(name = "instrument_id", nullable = false)
    private String instrumentId;

    @Column(name = "instrument")
    private String instrument;

    @Enumerated(EnumType.STRING)
    @Column(name = "critical_results")
    private CriticalResult criticalResults;

    @Column(name = "number_critical")
    private String numberCritical;

    @Column(name = "critical_comment")
    private String criticalComment;

    @NotNull
    @Column(name = "reporting_period_id", nullable = false)
    private String reportingPeriodId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReportTestPeformance id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaboratoryId() {
        return this.laboratoryId;
    }

    public ReportTestPeformance laboratoryId(String laboratoryId) {
        this.setLaboratoryId(laboratoryId);
        return this;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratory() {
        return this.laboratory;
    }

    public ReportTestPeformance laboratory(String laboratory) {
        this.setLaboratory(laboratory);
        return this;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public ReportTestPeformance departmentId(String departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return this.department;
    }

    public ReportTestPeformance department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSampleTypeId() {
        return this.sampleTypeId;
    }

    public ReportTestPeformance sampleTypeId(String sampleTypeId) {
        this.setSampleTypeId(sampleTypeId);
        return this;
    }

    public void setSampleTypeId(String sampleTypeId) {
        this.sampleTypeId = sampleTypeId;
    }

    public String getSampleType() {
        return this.sampleType;
    }

    public ReportTestPeformance sampleType(String sampleType) {
        this.setSampleType(sampleType);
        return this;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getTestId() {
        return this.testId;
    }

    public ReportTestPeformance testId(String testId) {
        this.setTestId(testId);
        return this;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTest() {
        return this.test;
    }

    public ReportTestPeformance test(String test) {
        this.setTest(test);
        return this;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTurnAroundTime() {
        return this.turnAroundTime;
    }

    public ReportTestPeformance turnAroundTime(String turnAroundTime) {
        this.setTurnAroundTime(turnAroundTime);
        return this;
    }

    public void setTurnAroundTime(String turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public String getNumberTested() {
        return this.numberTested;
    }

    public ReportTestPeformance numberTested(String numberTested) {
        this.setNumberTested(numberTested);
        return this;
    }

    public void setNumberTested(String numberTested) {
        this.numberTested = numberTested;
    }

    public String getNumberDispatched() {
        return this.numberDispatched;
    }

    public ReportTestPeformance numberDispatched(String numberDispatched) {
        this.setNumberDispatched(numberDispatched);
        return this;
    }

    public void setNumberDispatched(String numberDispatched) {
        this.numberDispatched = numberDispatched;
    }

    public String getNumberRejected() {
        return this.numberRejected;
    }

    public ReportTestPeformance numberRejected(String numberRejected) {
        this.setNumberRejected(numberRejected);
        return this;
    }

    public void setNumberRejected(String numberRejected) {
        this.numberRejected = numberRejected;
    }

    public String getInstrumentId() {
        return this.instrumentId;
    }

    public ReportTestPeformance instrumentId(String instrumentId) {
        this.setInstrumentId(instrumentId);
        return this;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrument() {
        return this.instrument;
    }

    public ReportTestPeformance instrument(String instrument) {
        this.setInstrument(instrument);
        return this;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public CriticalResult getCriticalResults() {
        return this.criticalResults;
    }

    public ReportTestPeformance criticalResults(CriticalResult criticalResults) {
        this.setCriticalResults(criticalResults);
        return this;
    }

    public void setCriticalResults(CriticalResult criticalResults) {
        this.criticalResults = criticalResults;
    }

    public String getNumberCritical() {
        return this.numberCritical;
    }

    public ReportTestPeformance numberCritical(String numberCritical) {
        this.setNumberCritical(numberCritical);
        return this;
    }

    public void setNumberCritical(String numberCritical) {
        this.numberCritical = numberCritical;
    }

    public String getCriticalComment() {
        return this.criticalComment;
    }

    public ReportTestPeformance criticalComment(String criticalComment) {
        this.setCriticalComment(criticalComment);
        return this;
    }

    public void setCriticalComment(String criticalComment) {
        this.criticalComment = criticalComment;
    }

    public String getReportingPeriodId() {
        return this.reportingPeriodId;
    }

    public ReportTestPeformance reportingPeriodId(String reportingPeriodId) {
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
        if (!(o instanceof ReportTestPeformance)) {
            return false;
        }
        return getId() != null && getId().equals(((ReportTestPeformance) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportTestPeformance{" +
            "id=" + getId() +
            ", laboratoryId='" + getLaboratoryId() + "'" +
            ", laboratory='" + getLaboratory() + "'" +
            ", departmentId='" + getDepartmentId() + "'" +
            ", department='" + getDepartment() + "'" +
            ", sampleTypeId='" + getSampleTypeId() + "'" +
            ", sampleType='" + getSampleType() + "'" +
            ", testId='" + getTestId() + "'" +
            ", test='" + getTest() + "'" +
            ", turnAroundTime='" + getTurnAroundTime() + "'" +
            ", numberTested='" + getNumberTested() + "'" +
            ", numberDispatched='" + getNumberDispatched() + "'" +
            ", numberRejected='" + getNumberRejected() + "'" +
            ", instrumentId='" + getInstrumentId() + "'" +
            ", instrument='" + getInstrument() + "'" +
            ", criticalResults='" + getCriticalResults() + "'" +
            ", numberCritical='" + getNumberCritical() + "'" +
            ", criticalComment='" + getCriticalComment() + "'" +
            ", reportingPeriodId='" + getReportingPeriodId() + "'" +
            "}";
    }
}
