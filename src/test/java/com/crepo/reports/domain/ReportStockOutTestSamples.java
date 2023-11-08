package com.crepo.reports.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ReportStockOutTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ReportStockOut getReportStockOutSample1() {
        return new ReportStockOut()
            .id(1L)
            .laboratoryId("laboratoryId1")
            .laboratory("laboratory1")
            .stockItemId("stockItemId1")
            .stockItem("stockItem1")
            .departmentId("departmentId1")
            .department("department1")
            .comment("comment1")
            .reportingPeriodId("reportingPeriodId1");
    }

    public static ReportStockOut getReportStockOutSample2() {
        return new ReportStockOut()
            .id(2L)
            .laboratoryId("laboratoryId2")
            .laboratory("laboratory2")
            .stockItemId("stockItemId2")
            .stockItem("stockItem2")
            .departmentId("departmentId2")
            .department("department2")
            .comment("comment2")
            .reportingPeriodId("reportingPeriodId2");
    }

    public static ReportStockOut getReportStockOutRandomSampleGenerator() {
        return new ReportStockOut()
            .id(longCount.incrementAndGet())
            .laboratoryId(UUID.randomUUID().toString())
            .laboratory(UUID.randomUUID().toString())
            .stockItemId(UUID.randomUUID().toString())
            .stockItem(UUID.randomUUID().toString())
            .departmentId(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString())
            .comment(UUID.randomUUID().toString())
            .reportingPeriodId(UUID.randomUUID().toString());
    }
}
