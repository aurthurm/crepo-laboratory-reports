package com.crepo.reports.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ReportTestPeformanceTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ReportTestPeformance getReportTestPeformanceSample1() {
        return new ReportTestPeformance()
            .id(1L)
            .laboratoryId("laboratoryId1")
            .laboratory("laboratory1")
            .departmentId("departmentId1")
            .department("department1")
            .sampleTypeId("sampleTypeId1")
            .sampleType("sampleType1")
            .testId("testId1")
            .test("test1")
            .turnAroundTime("turnAroundTime1")
            .numberTested("numberTested1")
            .numberDispatched("numberDispatched1")
            .numberRejected("numberRejected1")
            .instrumentId("instrumentId1")
            .instrument("instrument1")
            .numberCritical("numberCritical1")
            .criticalComment("criticalComment1")
            .reportingPeriodId("reportingPeriodId1");
    }

    public static ReportTestPeformance getReportTestPeformanceSample2() {
        return new ReportTestPeformance()
            .id(2L)
            .laboratoryId("laboratoryId2")
            .laboratory("laboratory2")
            .departmentId("departmentId2")
            .department("department2")
            .sampleTypeId("sampleTypeId2")
            .sampleType("sampleType2")
            .testId("testId2")
            .test("test2")
            .turnAroundTime("turnAroundTime2")
            .numberTested("numberTested2")
            .numberDispatched("numberDispatched2")
            .numberRejected("numberRejected2")
            .instrumentId("instrumentId2")
            .instrument("instrument2")
            .numberCritical("numberCritical2")
            .criticalComment("criticalComment2")
            .reportingPeriodId("reportingPeriodId2");
    }

    public static ReportTestPeformance getReportTestPeformanceRandomSampleGenerator() {
        return new ReportTestPeformance()
            .id(longCount.incrementAndGet())
            .laboratoryId(UUID.randomUUID().toString())
            .laboratory(UUID.randomUUID().toString())
            .departmentId(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString())
            .sampleTypeId(UUID.randomUUID().toString())
            .sampleType(UUID.randomUUID().toString())
            .testId(UUID.randomUUID().toString())
            .test(UUID.randomUUID().toString())
            .turnAroundTime(UUID.randomUUID().toString())
            .numberTested(UUID.randomUUID().toString())
            .numberDispatched(UUID.randomUUID().toString())
            .numberRejected(UUID.randomUUID().toString())
            .instrumentId(UUID.randomUUID().toString())
            .instrument(UUID.randomUUID().toString())
            .numberCritical(UUID.randomUUID().toString())
            .criticalComment(UUID.randomUUID().toString())
            .reportingPeriodId(UUID.randomUUID().toString());
    }
}
