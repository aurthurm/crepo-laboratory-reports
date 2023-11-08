package com.crepo.reports.domain;

import static com.crepo.reports.domain.ReportActivityUpdateTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.crepo.reports.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReportActivityUpdateTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportActivityUpdate.class);
        ReportActivityUpdate reportActivityUpdate1 = getReportActivityUpdateSample1();
        ReportActivityUpdate reportActivityUpdate2 = new ReportActivityUpdate();
        assertThat(reportActivityUpdate1).isNotEqualTo(reportActivityUpdate2);

        reportActivityUpdate2.setId(reportActivityUpdate1.getId());
        assertThat(reportActivityUpdate1).isEqualTo(reportActivityUpdate2);

        reportActivityUpdate2 = getReportActivityUpdateSample2();
        assertThat(reportActivityUpdate1).isNotEqualTo(reportActivityUpdate2);
    }
}
