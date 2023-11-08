package com.crepo.reports.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class StaffCompimentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static StaffCompiment getStaffCompimentSample1() {
        return new StaffCompiment()
            .id(1L)
            .laboratoryId("laboratoryId1")
            .laboratory("laboratory1")
            .departmentId("departmentId1")
            .department("department1")
            .scientistAvailable(1L)
            .scientistRequired(1L)
            .microscopitsAvailable(1L)
            .microscopitsRequired(1L)
            .labTechsAvailable(1L)
            .labTechsRequired(1L)
            .generalHandsAvailable(1L)
            .generalHandsRequired(1L);
    }

    public static StaffCompiment getStaffCompimentSample2() {
        return new StaffCompiment()
            .id(2L)
            .laboratoryId("laboratoryId2")
            .laboratory("laboratory2")
            .departmentId("departmentId2")
            .department("department2")
            .scientistAvailable(2L)
            .scientistRequired(2L)
            .microscopitsAvailable(2L)
            .microscopitsRequired(2L)
            .labTechsAvailable(2L)
            .labTechsRequired(2L)
            .generalHandsAvailable(2L)
            .generalHandsRequired(2L);
    }

    public static StaffCompiment getStaffCompimentRandomSampleGenerator() {
        return new StaffCompiment()
            .id(longCount.incrementAndGet())
            .laboratoryId(UUID.randomUUID().toString())
            .laboratory(UUID.randomUUID().toString())
            .departmentId(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString())
            .scientistAvailable(longCount.incrementAndGet())
            .scientistRequired(longCount.incrementAndGet())
            .microscopitsAvailable(longCount.incrementAndGet())
            .microscopitsRequired(longCount.incrementAndGet())
            .labTechsAvailable(longCount.incrementAndGet())
            .labTechsRequired(longCount.incrementAndGet())
            .generalHandsAvailable(longCount.incrementAndGet())
            .generalHandsRequired(longCount.incrementAndGet());
    }
}
