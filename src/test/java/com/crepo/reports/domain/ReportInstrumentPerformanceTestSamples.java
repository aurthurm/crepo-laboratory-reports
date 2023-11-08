package com.crepo.reports.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ReportInstrumentPerformanceTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ReportInstrumentPerformance getReportInstrumentPerformanceSample1() {
        return new ReportInstrumentPerformance()
            .id(1L)
            .laboratoryId("laboratoryId1")
            .laboratory("laboratory1")
            .departmentId("departmentId1")
            .department("department1")
            .instrumentId("instrumentId1")
            .instrument("instrument1")
            .status("status1")
            .uptime("uptime1")
            .downtime("downtime1")
            .serviceStatus("serviceStatus1")
            .caliberationStatus("caliberationStatus1")
            .comment("comment1")
            .reportingPeriodId("reportingPeriodId1");
    }

    public static ReportInstrumentPerformance getReportInstrumentPerformanceSample2() {
        return new ReportInstrumentPerformance()
            .id(2L)
            .laboratoryId("laboratoryId2")
            .laboratory("laboratory2")
            .departmentId("departmentId2")
            .department("department2")
            .instrumentId("instrumentId2")
            .instrument("instrument2")
            .status("status2")
            .uptime("uptime2")
            .downtime("downtime2")
            .serviceStatus("serviceStatus2")
            .caliberationStatus("caliberationStatus2")
            .comment("comment2")
            .reportingPeriodId("reportingPeriodId2");
    }

    public static ReportInstrumentPerformance getReportInstrumentPerformanceRandomSampleGenerator() {
        return new ReportInstrumentPerformance()
            .id(longCount.incrementAndGet())
            .laboratoryId(UUID.randomUUID().toString())
            .laboratory(UUID.randomUUID().toString())
            .departmentId(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString())
            .instrumentId(UUID.randomUUID().toString())
            .instrument(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString())
            .uptime(UUID.randomUUID().toString())
            .downtime(UUID.randomUUID().toString())
            .serviceStatus(UUID.randomUUID().toString())
            .caliberationStatus(UUID.randomUUID().toString())
            .comment(UUID.randomUUID().toString())
            .reportingPeriodId(UUID.randomUUID().toString());
    }
}
