package com.crepo.reports.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LaboratoryStockTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LaboratoryStock getLaboratoryStockSample1() {
        return new LaboratoryStock()
            .id(1L)
            .laboratoryId("laboratoryId1")
            .laboratory("laboratory1")
            .stockItemId("stockItemId1")
            .stockItem("stockItem1")
            .departmentId("departmentId1")
            .department("department1");
    }

    public static LaboratoryStock getLaboratoryStockSample2() {
        return new LaboratoryStock()
            .id(2L)
            .laboratoryId("laboratoryId2")
            .laboratory("laboratory2")
            .stockItemId("stockItemId2")
            .stockItem("stockItem2")
            .departmentId("departmentId2")
            .department("department2");
    }

    public static LaboratoryStock getLaboratoryStockRandomSampleGenerator() {
        return new LaboratoryStock()
            .id(longCount.incrementAndGet())
            .laboratoryId(UUID.randomUUID().toString())
            .laboratory(UUID.randomUUID().toString())
            .stockItemId(UUID.randomUUID().toString())
            .stockItem(UUID.randomUUID().toString())
            .departmentId(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString());
    }
}
