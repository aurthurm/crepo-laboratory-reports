package com.crepo.reports.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ReportingPeriodTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ReportingPeriod getReportingPeriodSample1() {
        return new ReportingPeriod().id(1L).day(1).week(1).month("month1").year(1);
    }

    public static ReportingPeriod getReportingPeriodSample2() {
        return new ReportingPeriod().id(2L).day(2).week(2).month("month2").year(2);
    }

    public static ReportingPeriod getReportingPeriodRandomSampleGenerator() {
        return new ReportingPeriod()
            .id(longCount.incrementAndGet())
            .day(intCount.incrementAndGet())
            .week(intCount.incrementAndGet())
            .month(UUID.randomUUID().toString())
            .year(intCount.incrementAndGet());
    }
}
