package com.crepo.reports.domain;

import static com.crepo.reports.domain.ReportingPeriodTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.crepo.reports.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReportingPeriodTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportingPeriod.class);
        ReportingPeriod reportingPeriod1 = getReportingPeriodSample1();
        ReportingPeriod reportingPeriod2 = new ReportingPeriod();
        assertThat(reportingPeriod1).isNotEqualTo(reportingPeriod2);

        reportingPeriod2.setId(reportingPeriod1.getId());
        assertThat(reportingPeriod1).isEqualTo(reportingPeriod2);

        reportingPeriod2 = getReportingPeriodSample2();
        assertThat(reportingPeriod1).isNotEqualTo(reportingPeriod2);
    }
}
