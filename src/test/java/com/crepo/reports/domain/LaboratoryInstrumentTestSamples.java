package com.crepo.reports.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LaboratoryInstrumentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LaboratoryInstrument getLaboratoryInstrumentSample1() {
        return new LaboratoryInstrument()
            .id(1L)
            .laboratoryId("laboratoryId1")
            .laboratory("laboratory1")
            .instrumentId("instrumentId1")
            .instrument("instrument1")
            .departmentId("departmentId1")
            .department("department1");
    }

    public static LaboratoryInstrument getLaboratoryInstrumentSample2() {
        return new LaboratoryInstrument()
            .id(2L)
            .laboratoryId("laboratoryId2")
            .laboratory("laboratory2")
            .instrumentId("instrumentId2")
            .instrument("instrument2")
            .departmentId("departmentId2")
            .department("department2");
    }

    public static LaboratoryInstrument getLaboratoryInstrumentRandomSampleGenerator() {
        return new LaboratoryInstrument()
            .id(longCount.incrementAndGet())
            .laboratoryId(UUID.randomUUID().toString())
            .laboratory(UUID.randomUUID().toString())
            .instrumentId(UUID.randomUUID().toString())
            .instrument(UUID.randomUUID().toString())
            .departmentId(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString());
    }
}
