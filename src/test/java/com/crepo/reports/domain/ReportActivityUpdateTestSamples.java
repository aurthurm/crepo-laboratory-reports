package com.crepo.reports.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ReportActivityUpdateTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ReportActivityUpdate getReportActivityUpdateSample1() {
        return new ReportActivityUpdate()
            .id(1L)
            .laboratoryId("laboratoryId1")
            .laboratory("laboratory1")
            .activity("activity1")
            .activityDetails("activityDetails1")
            .outcomes("outcomes1")
            .comments("comments1")
            .reportingPeriodId("reportingPeriodId1");
    }

    public static ReportActivityUpdate getReportActivityUpdateSample2() {
        return new ReportActivityUpdate()
            .id(2L)
            .laboratoryId("laboratoryId2")
            .laboratory("laboratory2")
            .activity("activity2")
            .activityDetails("activityDetails2")
            .outcomes("outcomes2")
            .comments("comments2")
            .reportingPeriodId("reportingPeriodId2");
    }

    public static ReportActivityUpdate getReportActivityUpdateRandomSampleGenerator() {
        return new ReportActivityUpdate()
            .id(longCount.incrementAndGet())
            .laboratoryId(UUID.randomUUID().toString())
            .laboratory(UUID.randomUUID().toString())
            .activity(UUID.randomUUID().toString())
            .activityDetails(UUID.randomUUID().toString())
            .outcomes(UUID.randomUUID().toString())
            .comments(UUID.randomUUID().toString())
            .reportingPeriodId(UUID.randomUUID().toString());
    }
}
