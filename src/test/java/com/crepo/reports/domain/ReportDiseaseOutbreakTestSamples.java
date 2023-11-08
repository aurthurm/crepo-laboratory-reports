package com.crepo.reports.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ReportDiseaseOutbreakTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ReportDiseaseOutbreak getReportDiseaseOutbreakSample1() {
        return new ReportDiseaseOutbreak()
            .id(1L)
            .laboratoryId("laboratoryId1")
            .laboratory("laboratory1")
            .disease("disease1")
            .comment("comment1")
            .reportingPeriodId("reportingPeriodId1");
    }

    public static ReportDiseaseOutbreak getReportDiseaseOutbreakSample2() {
        return new ReportDiseaseOutbreak()
            .id(2L)
            .laboratoryId("laboratoryId2")
            .laboratory("laboratory2")
            .disease("disease2")
            .comment("comment2")
            .reportingPeriodId("reportingPeriodId2");
    }

    public static ReportDiseaseOutbreak getReportDiseaseOutbreakRandomSampleGenerator() {
        return new ReportDiseaseOutbreak()
            .id(longCount.incrementAndGet())
            .laboratoryId(UUID.randomUUID().toString())
            .laboratory(UUID.randomUUID().toString())
            .disease(UUID.randomUUID().toString())
            .comment(UUID.randomUUID().toString())
            .reportingPeriodId(UUID.randomUUID().toString());
    }
}
