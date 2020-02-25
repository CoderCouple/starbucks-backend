package com.starbucks.persistance;

public interface JDOConfig {
    String getType();
    String getUrlOptionalParams();
    String getGroupName();
    String getHost();
    String getPort();
    String getName();
    String getUser();
    String getPassword();
    String getAutoCreate();
    boolean isReadOnly();
}
