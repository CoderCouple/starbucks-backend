package com.starbucks.config;

public interface SharedConfig {

    String getString(String key);

    String getStringWithDefault(String key, String defaultValue);

    Integer getInteger(String key);

    Integer getIntegerWithDefault(String key, Integer defaultValue);

    Boolean getBoolean(String key);

    Boolean getBooleanWithDefault(String key, Boolean defaultValue);

    Float getFloat(String key);

    Float getFloatWithDefault(String key, Float defaultValue);

    Double getDouble(String key);

    Double getDoubleWithDefault(String key, Double defaultValue);
}
