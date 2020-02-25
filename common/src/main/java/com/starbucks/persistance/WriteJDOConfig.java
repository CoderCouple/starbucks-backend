package com.starbucks.persistance;

import com.codahale.metrics.MetricRegistry;
import com.starbucks.config.SharedConfig;

public class WriteJDOConfig extends BaseJDOConfig {

    private static final String CONFIG_KEY = "write";
    private static final String TRANSACTION_POOL_NAME = "StarbucksPool";

    public WriteJDOConfig(final SharedConfig config, final MetricRegistry metricRegistry) {
        super(config, metricRegistry, false, CONFIG_KEY, TRANSACTION_POOL_NAME);
    }
}
