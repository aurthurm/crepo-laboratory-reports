package com.crepo.reports.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LaboratoryTestTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LaboratoryTest getLaboratoryTestSample1() {
        return new LaboratoryTest()
            .id(1L)
            .laboratoryId("laboratoryId1")
            .laboratory("laboratory1")
            .testId("testId1")
            .test("test1")
            .departmentId("departmentId1")
            .department("department1");
    }

    public static LaboratoryTest getLaboratoryTestSample2() {
        return new LaboratoryTest()
            .id(2L)
            .laboratoryId("laboratoryId2")
            .laboratory("laboratory2")
            .testId("testId2")
            .test("test2")
            .departmentId("departmentId2")
            .department("department2");
    }

    public static LaboratoryTest getLaboratoryTestRandomSampleGenerator() {
        return new LaboratoryTest()
            .id(longCount.incrementAndGet())
            .laboratoryId(UUID.randomUUID().toString())
            .laboratory(UUID.randomUUID().toString())
            .testId(UUID.randomUUID().toString())
            .test(UUID.randomUUID().toString())
            .departmentId(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString());
    }
}
