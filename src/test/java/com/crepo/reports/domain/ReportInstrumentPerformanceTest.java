package com.crepo.reports.domain;

import static com.crepo.reports.domain.ReportInstrumentPerformanceTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.crepo.reports.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReportInstrumentPerformanceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportInstrumentPerformance.class);
        ReportInstrumentPerformance reportInstrumentPerformance1 = getReportInstrumentPerformanceSample1();
        ReportInstrumentPerformance reportInstrumentPerformance2 = new ReportInstrumentPerformance();
        assertThat(reportInstrumentPerformance1).isNotEqualTo(reportInstrumentPerformance2);

        reportInstrumentPerformance2.setId(reportInstrumentPerformance1.getId());
        assertThat(reportInstrumentPerformance1).isEqualTo(reportInstrumentPerformance2);

        reportInstrumentPerformance2 = getReportInstrumentPerformanceSample2();
        assertThat(reportInstrumentPerformance1).isNotEqualTo(reportInstrumentPerformance2);
    }
}
