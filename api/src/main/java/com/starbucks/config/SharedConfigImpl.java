package com.starbucks.config;

import com.google.inject.Inject;

public class SharedConfigImpl implements SharedConfig {

    private ConfigReader configReader;

    @Inject
    public SharedConfigImpl(final ConfigReader configReader) {
        this.configReader = configReader;
    }

    @Override
    public String getString(final String key) {
        return configReader.config.getString(key);
    }

    @Override
    public String getStringWithDefault(final String key, final String defaultValue) {
        return configReader.config.getString(key, defaultValue);
    }

    @Override
    public Integer getInteger(final String key) {
        return configReader.config.getInt(key);
    }

    @Override
    public Integer getIntegerWithDefault(final String key, final Integer defaultValue) {
        return configReader.config.getInt(key, defaultValue);
    }

    @Override
    public Boolean getBoolean(final String key) {
        Boolean val =  configReader.config.getBoolean(key);
        return val;
    }

    @Override
    public Boolean getBooleanWithDefault(final String key, final Boolean defaultValue) {
        return configReader.config.getBoolean(key);
    }

    @Override
    public Float getFloat(final String key) {
        return configReader.config.getFloat(key);
    }

    @Override
    public Float getFloatWithDefault(final String key, final Float defaultValue) {
        return configReader.config.getFloat(key, defaultValue);
    }

    @Override
    public Double getDouble(final String key) {
        return configReader.config.getDouble(key);
    }

    @Override
    public Double getDoubleWithDefault(final String key, final Double defaultValue) {
        return configReader.config.getDouble(key, defaultValue);
    }
}
