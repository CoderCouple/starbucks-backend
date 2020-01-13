package com.starbucks.config;

import com.google.inject.Inject;

public class SharedConfigImpl implements SharedConfig {

    private ConfigReader configReader;

    @Inject
    public SharedConfigImpl(ConfigReader configReader) {
        this.configReader = configReader;
    }

    @Override
    public String getString(String key) {
        return configReader.config.getString(key);
    }

    @Override
    public String getStringWithDefault(String key, String defaultValue) {
        return configReader.config.getString(key,defaultValue);
    }

    @Override
    public Integer getInteger(String key) {
        return configReader.config.getInt(key);
    }

    @Override
    public Integer getIntegerWithDefault(String key, Integer defaultValue) {
        return configReader.config.getInt(key, defaultValue);
    }

    @Override
    public Boolean getBoolean(String key) {
        Boolean val =  configReader.config.getBoolean(key);
        return val;
    }

    @Override
    public Boolean getBooleanWithDefault(String key, Boolean defaultValue) {
        return configReader.config.getBoolean(key);
    }

    @Override
    public Float getFloat(String key) {
        return configReader.config.getFloat(key);
    }

    @Override
    public Float getFloatWithDefault(String key, Float defaultValue) {
        return configReader.config.getFloat(key,defaultValue);
    }

    @Override
    public Double getDouble(String key) {
        return configReader.config.getDouble(key);
    }

    @Override
    public Double getDoubleWithDefault(String key, Double defaultValue) {
        return configReader.config.getDouble(key,defaultValue);
    }
}
