package com.starbucks.config;

import com.google.common.base.Strings;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

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
        this.env = Strings.isNullOrEmpty(System.getenv("env")) ? Env.DEV.getName() : System.getenv("env");
    }

    public String getEnv(final String env) {
        return configReader.config.getString(env, "dev");
    }

    public String appName(final String key) {
        return configReader.config.getString(key);
    }

    @Override
    public String getString(final String key) {
        return configReader.config.getString(getEnvPrefixedKey(key));
    }

    @Override
    public String getStringOrDefault(final String key, final String defaultValue) {
        return configReader.config.getString(getEnvPrefixedKey(key), defaultValue);
    }

    @Override
    public Integer getInteger(final String key) {
        return configReader.config.getInt(getEnvPrefixedKey(key));
    }

    @Override
    public Integer getIntegerOrDefault(final String key, final Integer defaultValue) {
        return configReader.config.getInt(getEnvPrefixedKey(key), defaultValue);
    }

    @Override
    public Boolean getBoolean(final String key) {
        Boolean val =  configReader.config.getBoolean(getEnvPrefixedKey(key));
        return val;
    }

    @Override
    public Boolean getBooleanOrDefault(final String key, final Boolean defaultValue) {
        return configReader.config.getBoolean(getEnvPrefixedKey(key));
    }

    @Override
    public Float getFloat(final String key) {
        return configReader.config.getFloat(getEnvPrefixedKey(key));
    }

    @Override
    public Float getFloatOrDefault(final String key, final Float defaultValue) {
        return configReader.config.getFloat(getEnvPrefixedKey(key), defaultValue);
    }

    @Override
    public Double getDouble(final String key) {
        return configReader.config.getDouble(getEnvPrefixedKey(key));
    }

    @Override
    public Double getDoubleOrDefault(final String key, final Double defaultValue) {
        return configReader.config.getDouble(getEnvPrefixedKey(key), defaultValue);
    }

    @Override
    public Boolean isMac() {
        return this.isMac;
    }

    @Override
    public Boolean keyExists(final String key) {
        return configReader.config.containsKey(getEnvPrefixedKey(key));
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
    public List<String> getList(final String key) {
        List<String> list = new ArrayList<>();
        configReader.config.getList(getEnvPrefixedKey(key)).forEach(k -> list.add(k.toString()));
        return list;
    }

    @Override
    public String getEnv() {
        return this.env;
    }

    @Override
    public String getGroupName() {
        return this.group.getName();
    }

    private String getEnvPrefixedKey(final String key) {
        return this.env + "." + key;
    }
}
