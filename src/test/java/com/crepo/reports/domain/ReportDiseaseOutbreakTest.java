package com.crepo.reports.domain;

import static com.crepo.reports.domain.ReportDiseaseOutbreakTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.crepo.reports.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReportDiseaseOutbreakTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportDiseaseOutbreak.class);
        ReportDiseaseOutbreak reportDiseaseOutbreak1 = getReportDiseaseOutbreakSample1();
        ReportDiseaseOutbreak reportDiseaseOutbreak2 = new ReportDiseaseOutbreak();
        assertThat(reportDiseaseOutbreak1).isNotEqualTo(reportDiseaseOutbreak2);

        reportDiseaseOutbreak2.setId(reportDiseaseOutbreak1.getId());
        assertThat(reportDiseaseOutbreak1).isEqualTo(reportDiseaseOutbreak2);

        reportDiseaseOutbreak2 = getReportDiseaseOutbreakSample2();
        assertThat(reportDiseaseOutbreak1).isNotEqualTo(reportDiseaseOutbreak2);
    }
}
