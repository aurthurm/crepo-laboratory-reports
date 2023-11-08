package com.crepo.reports.domain;

import static com.crepo.reports.domain.ReportStockOutTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.crepo.reports.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReportStockOutTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportStockOut.class);
        ReportStockOut reportStockOut1 = getReportStockOutSample1();
        ReportStockOut reportStockOut2 = new ReportStockOut();
        assertThat(reportStockOut1).isNotEqualTo(reportStockOut2);

        reportStockOut2.setId(reportStockOut1.getId());
        assertThat(reportStockOut1).isEqualTo(reportStockOut2);

        reportStockOut2 = getReportStockOutSample2();
        assertThat(reportStockOut1).isNotEqualTo(reportStockOut2);
    }
}
