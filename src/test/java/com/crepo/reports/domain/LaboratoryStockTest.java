package com.crepo.reports.domain;

import static com.crepo.reports.domain.LaboratoryStockTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.crepo.reports.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LaboratoryStockTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LaboratoryStock.class);
        LaboratoryStock laboratoryStock1 = getLaboratoryStockSample1();
        LaboratoryStock laboratoryStock2 = new LaboratoryStock();
        assertThat(laboratoryStock1).isNotEqualTo(laboratoryStock2);

        laboratoryStock2.setId(laboratoryStock1.getId());
        assertThat(laboratoryStock1).isEqualTo(laboratoryStock2);

        laboratoryStock2 = getLaboratoryStockSample2();
        assertThat(laboratoryStock1).isNotEqualTo(laboratoryStock2);
    }
}
