package com.crepo.reports.domain;

import static com.crepo.reports.domain.LaboratoryTestTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.crepo.reports.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LaboratoryTestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LaboratoryTest.class);
        LaboratoryTest laboratoryTest1 = getLaboratoryTestSample1();
        LaboratoryTest laboratoryTest2 = new LaboratoryTest();
        assertThat(laboratoryTest1).isNotEqualTo(laboratoryTest2);

        laboratoryTest2.setId(laboratoryTest1.getId());
        assertThat(laboratoryTest1).isEqualTo(laboratoryTest2);

        laboratoryTest2 = getLaboratoryTestSample2();
        assertThat(laboratoryTest1).isNotEqualTo(laboratoryTest2);
    }
}
