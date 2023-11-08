package com.crepo.reports.domain;

import com.crepo.reports.domain.enumeration.Outbreak;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ReportDiseaseOutbreak.
 */
@Entity
@Table(name = "report_disease_outbreak")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReportDiseaseOutbreak implements Serializable {

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

    @Enumerated(EnumType.STRING)
    @Column(name = "outbreak")
    private Outbreak outbreak;

    @Column(name = "disease")
    private String disease;

    @Column(name = "comment")
    private String comment;

    @NotNull
    @Column(name = "reporting_period_id", nullable = false)
    private String reportingPeriodId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReportDiseaseOutbreak id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaboratoryId() {
        return this.laboratoryId;
    }

    public ReportDiseaseOutbreak laboratoryId(String laboratoryId) {
        this.setLaboratoryId(laboratoryId);
        return this;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratory() {
        return this.laboratory;
    }

    public ReportDiseaseOutbreak laboratory(String laboratory) {
        this.setLaboratory(laboratory);
        return this;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public Outbreak getOutbreak() {
        return this.outbreak;
    }

    public ReportDiseaseOutbreak outbreak(Outbreak outbreak) {
        this.setOutbreak(outbreak);
        return this;
    }

    public void setOutbreak(Outbreak outbreak) {
        this.outbreak = outbreak;
    }

    public String getDisease() {
        return this.disease;
    }

    public ReportDiseaseOutbreak disease(String disease) {
        this.setDisease(disease);
        return this;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getComment() {
        return this.comment;
    }

    public ReportDiseaseOutbreak comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReportingPeriodId() {
        return this.reportingPeriodId;
    }

    public ReportDiseaseOutbreak reportingPeriodId(String reportingPeriodId) {
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
        if (!(o instanceof ReportDiseaseOutbreak)) {
            return false;
        }
        return getId() != null && getId().equals(((ReportDiseaseOutbreak) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportDiseaseOutbreak{" +
            "id=" + getId() +
            ", laboratoryId='" + getLaboratoryId() + "'" +
            ", laboratory='" + getLaboratory() + "'" +
            ", outbreak='" + getOutbreak() + "'" +
            ", disease='" + getDisease() + "'" +
            ", comment='" + getComment() + "'" +
            ", reportingPeriodId='" + getReportingPeriodId() + "'" +
            "}";
    }
}
