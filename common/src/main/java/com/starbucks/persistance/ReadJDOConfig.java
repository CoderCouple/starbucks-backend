package com.starbucks.persistance;

import com.codahale.metrics.MetricRegistry;
import com.starbucks.config.SharedConfig;

public class ReadJDOConfig extends BaseJDOConfig {

    private static final String CONFIG_KEY = "read";

    public ReadJDOConfig(final SharedConfig config, final MetricRegistry metricRegistry) {
        super(config, metricRegistry, true, CONFIG_KEY);
    }
}
