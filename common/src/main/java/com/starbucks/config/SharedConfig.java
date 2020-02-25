package com.starbucks.config;

public interface SharedConfig {

    Boolean isMac();

    Boolean keyExists(String key);

    Boolean isDevEnv();

    Group getGroup();

    String getEnv();

    String getGroupName();

    String getString(String key);

    String getStringOrDefault(String key, String defaultValue);

    Integer getInteger(String key);

    Integer getIntegerOrDefault(String key, Integer defaultValue);

    Boolean getBoolean(String key);

    Boolean getBooleanOrDefault(String key, Boolean defaultValue);

    Float getFloat(String key);

    Float getFloatOrDefault(String key, Float defaultValue);

    Double getDouble(String key);

    Double getDoubleOrDefault(String key, Double defaultValue);

    enum Group {
        MAIN("main"),
        TEST("test");

        private final String name;

        Group(final String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    enum Env {
        DEV("dev"),
        DOCKER("docker"),
        PROD("prod");

        private final String name;

        Env(final String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
