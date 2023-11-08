package com.crepo.reports.domain;

import static com.crepo.reports.domain.LaboratoryInstrumentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.crepo.reports.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LaboratoryInstrumentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LaboratoryInstrument.class);
        LaboratoryInstrument laboratoryInstrument1 = getLaboratoryInstrumentSample1();
        LaboratoryInstrument laboratoryInstrument2 = new LaboratoryInstrument();
        assertThat(laboratoryInstrument1).isNotEqualTo(laboratoryInstrument2);

        laboratoryInstrument2.setId(laboratoryInstrument1.getId());
        assertThat(laboratoryInstrument1).isEqualTo(laboratoryInstrument2);

        laboratoryInstrument2 = getLaboratoryInstrumentSample2();
        assertThat(laboratoryInstrument1).isNotEqualTo(laboratoryInstrument2);
    }
}
