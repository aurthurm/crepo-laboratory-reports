package com.crepo.reports.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ReportActivityUpdate.
 */
@Entity
@Table(name = "report_activity_update")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReportActivityUpdate implements Serializable {

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

    @Column(name = "activity")
    private String activity;

    @Column(name = "activity_details")
    private String activityDetails;

    @Column(name = "outcomes")
    private String outcomes;

    @Column(name = "comments")
    private String comments;

    @NotNull
    @Column(name = "reporting_period_id", nullable = false)
    private String reportingPeriodId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReportActivityUpdate id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaboratoryId() {
        return this.laboratoryId;
    }

    public ReportActivityUpdate laboratoryId(String laboratoryId) {
        this.setLaboratoryId(laboratoryId);
        return this;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratory() {
        return this.laboratory;
    }

    public ReportActivityUpdate laboratory(String laboratory) {
        this.setLaboratory(laboratory);
        return this;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getActivity() {
        return this.activity;
    }

    public ReportActivityUpdate activity(String activity) {
        this.setActivity(activity);
        return this;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivityDetails() {
        return this.activityDetails;
    }

    public ReportActivityUpdate activityDetails(String activityDetails) {
        this.setActivityDetails(activityDetails);
        return this;
    }

    public void setActivityDetails(String activityDetails) {
        this.activityDetails = activityDetails;
    }

    public String getOutcomes() {
        return this.outcomes;
    }

    public ReportActivityUpdate outcomes(String outcomes) {
        this.setOutcomes(outcomes);
        return this;
    }

    public void setOutcomes(String outcomes) {
        this.outcomes = outcomes;
    }

    public String getComments() {
        return this.comments;
    }

    public ReportActivityUpdate comments(String comments) {
        this.setComments(comments);
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReportingPeriodId() {
        return this.reportingPeriodId;
    }

    public ReportActivityUpdate reportingPeriodId(String reportingPeriodId) {
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
        if (!(o instanceof ReportActivityUpdate)) {
            return false;
        }
        return getId() != null && getId().equals(((ReportActivityUpdate) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportActivityUpdate{" +
            "id=" + getId() +
            ", laboratoryId='" + getLaboratoryId() + "'" +
            ", laboratory='" + getLaboratory() + "'" +
            ", activity='" + getActivity() + "'" +
            ", activityDetails='" + getActivityDetails() + "'" +
            ", outcomes='" + getOutcomes() + "'" +
            ", comments='" + getComments() + "'" +
            ", reportingPeriodId='" + getReportingPeriodId() + "'" +
            "}";
    }
}
