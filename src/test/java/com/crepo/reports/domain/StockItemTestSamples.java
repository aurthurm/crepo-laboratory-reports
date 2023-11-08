package com.crepo.reports.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class StockItemTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static StockItem getStockItemSample1() {
        return new StockItem().id(1L).name("name1").description("description1");
    }

    public static StockItem getStockItemSample2() {
        return new StockItem().id(2L).name("name2").description("description2");
    }

    public static StockItem getStockItemRandomSampleGenerator() {
        return new StockItem().id(longCount.incrementAndGet()).name(UUID.randomUUID().toString()).description(UUID.randomUUID().toString());
    }
}
