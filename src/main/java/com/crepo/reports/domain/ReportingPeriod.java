package com.crepo.reports.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ReportingPeriod.
 */
@Entity
@Table(name = "reporting_period")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReportingPeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "day")
    private Integer day;

    @Column(name = "week")
    private Integer week;

    @NotNull
    @Column(name = "month", nullable = false)
    private String month;

    @NotNull
    @Column(name = "year", nullable = false)
    private Integer year;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReportingPeriod id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDay() {
        return this.day;
    }

    public ReportingPeriod day(Integer day) {
        this.setDay(day);
        return this;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getWeek() {
        return this.week;
    }

    public ReportingPeriod week(Integer week) {
        this.setWeek(week);
        return this;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getMonth() {
        return this.month;
    }

    public ReportingPeriod month(String month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getYear() {
        return this.year;
    }

    public ReportingPeriod year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportingPeriod)) {
            return false;
        }
        return getId() != null && getId().equals(((ReportingPeriod) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportingPeriod{" +
            "id=" + getId() +
            ", day=" + getDay() +
            ", week=" + getWeek() +
            ", month='" + getMonth() + "'" +
            ", year=" + getYear() +
            "}";
    }
}
