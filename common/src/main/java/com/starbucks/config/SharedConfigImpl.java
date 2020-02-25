package com.starbucks.config;

import com.google.common.base.Strings;
import com.google.inject.Inject;

public class SharedConfigImpl implements SharedConfig {

    private final ConfigReader configReader;
    private final boolean isMac;
    private final boolean isDevEnv;
    private final String env;
    private final Group group;


    @Inject
    public SharedConfigImpl(final ConfigReader configReader, final Group group) {
        this.configReader = configReader;
        this.group = group;
        this.isMac = ("mac os x").equalsIgnoreCase(System.getProperty("os.name"));
        this.isDevEnv = ("dev").equalsIgnoreCase(getEnv("dev"));
        this.env = Strings.isNullOrEmpty(System.getProperty("env")) ? Env.DEV.getName() : System.getProperty("env");
    }

    public String getEnv(final String env) {
        return configReader.config.getString(env, "dev");
    }

    @Override
    public String getString(final String key) {
        return configReader.config.getString(key);
    }

    @Override
    public String getStringOrDefault(final String key, final String defaultValue) {
        return configReader.config.getString(key, defaultValue);
    }

    @Override
    public Integer getInteger(final String key) {
        return configReader.config.getInt(key);
    }

    @Override
    public Integer getIntegerOrDefault(final String key, final Integer defaultValue) {
        return configReader.config.getInt(key, defaultValue);
    }

    @Override
    public Boolean getBoolean(final String key) {
        Boolean val =  configReader.config.getBoolean(key);
        return val;
    }

    @Override
    public Boolean getBooleanOrDefault(final String key, final Boolean defaultValue) {
        return configReader.config.getBoolean(key);
    }

    @Override
    public Float getFloat(final String key) {
        return configReader.config.getFloat(key);
    }

    @Override
    public Float getFloatOrDefault(final String key, final Float defaultValue) {
        return configReader.config.getFloat(key, defaultValue);
    }

    @Override
    public Double getDouble(final String key) {
        return configReader.config.getDouble(key);
    }

    @Override
    public Double getDoubleOrDefault(final String key, final Double defaultValue) {
        return configReader.config.getDouble(key, defaultValue);
    }

    @Override
    public Boolean isMac() {
        return this.isMac;
    }

    @Override
    public Boolean keyExists(final String key) {
        return configReader.config.containsKey(key);
    }

    @Override
    public Boolean isDevEnv() {
        return this.isDevEnv;
    }

    @Override
    public Group getGroup() {
        return this.group;
    }

    @Override
    public String getEnv() {
        return this.env;
    }

    @Override
    public String getGroupName() {
        return this.group.getName();
    }
}
