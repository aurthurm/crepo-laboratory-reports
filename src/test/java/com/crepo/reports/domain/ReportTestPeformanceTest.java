package com.crepo.reports.domain;

import static com.crepo.reports.domain.ReportTestPeformanceTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.crepo.reports.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReportTestPeformanceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportTestPeformance.class);
        ReportTestPeformance reportTestPeformance1 = getReportTestPeformanceSample1();
        ReportTestPeformance reportTestPeformance2 = new ReportTestPeformance();
        assertThat(reportTestPeformance1).isNotEqualTo(reportTestPeformance2);

        reportTestPeformance2.setId(reportTestPeformance1.getId());
        assertThat(reportTestPeformance1).isEqualTo(reportTestPeformance2);

        reportTestPeformance2 = getReportTestPeformanceSample2();
        assertThat(reportTestPeformance1).isNotEqualTo(reportTestPeformance2);
    }
}
