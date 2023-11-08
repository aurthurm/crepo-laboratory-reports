package com.crepo.reports.domain;

import static com.crepo.reports.domain.StaffCompimentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.crepo.reports.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StaffCompimentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StaffCompiment.class);
        StaffCompiment staffCompiment1 = getStaffCompimentSample1();
        StaffCompiment staffCompiment2 = new StaffCompiment();
        assertThat(staffCompiment1).isNotEqualTo(staffCompiment2);

        staffCompiment2.setId(staffCompiment1.getId());
        assertThat(staffCompiment1).isEqualTo(staffCompiment2);

        staffCompiment2 = getStaffCompimentSample2();
        assertThat(staffCompiment1).isNotEqualTo(staffCompiment2);
    }
}
